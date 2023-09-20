package dev.ehyeon.move;

import dev.ehyeon.move.entity.Member;
import dev.ehyeon.move.entity.MoveStop;
import dev.ehyeon.move.entity.Role;
import dev.ehyeon.move.entity.VisitedMoveStop;
import dev.ehyeon.move.repository.MemberRepository;
import dev.ehyeon.move.repository.MoveStopRepository;
import dev.ehyeon.move.repository.VisitedMoveStopRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class VisitedMoveStopTest {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    MoveStopRepository moveStopRepository;

    @Autowired
    VisitedMoveStopRepository visitedMoveStopRepository;

    @Test
    void findVisitedMoveStopByMemberIdAndMoveStopIdTest() throws ParseException {
        Member member1 = memberRepository.save(new Member("email1", "password1", Role.MEMBER));
        Member member2 = memberRepository.save(new Member("email2", "password2", Role.MEMBER));

        MoveStop moveStop1 = moveStopRepository.save(new MoveStop("test1", "address1", 0, 0));
        MoveStop moveStop2 = moveStopRepository.save(new MoveStop("test2", "address2", 0, 0));

        visitedMoveStopRepository.save(new VisitedMoveStop(member1, moveStop1, StringToLocalDateTime("2000-01-01T01:00:00")));
        visitedMoveStopRepository.save(new VisitedMoveStop(member1, moveStop2, StringToLocalDateTime("2000-01-01T01:10:00")));
        visitedMoveStopRepository.save(new VisitedMoveStop(member2, moveStop1, StringToLocalDateTime("2000-01-01T01:00:00")));

        Optional<VisitedMoveStop> foundVisitedMoveStopOptional = visitedMoveStopRepository.findVisitedMoveStopByMemberIdAndMoveStopId(member1.getId(), moveStop1.getId());

        if (foundVisitedMoveStopOptional.isEmpty()) {
            Assertions.fail();
        }

        VisitedMoveStop foundVisitedMoveStop = foundVisitedMoveStopOptional.get();

        log.info("foundVisitedMoveStop: " + foundVisitedMoveStop.getMember().getEmail() + ", " + foundVisitedMoveStop.getMoveStop().getName() + ", " + foundVisitedMoveStop.getDateOfLastVisit());

        assertThat(foundVisitedMoveStop.getMember().getId()).isEqualTo(member1.getId());
        assertThat(foundVisitedMoveStop.getMoveStop().getId()).isEqualTo(moveStop1.getId());
        assertThat(foundVisitedMoveStop.getDateOfLastVisit()).isEqualTo(StringToLocalDateTime("2000-01-01T01:00:00"));
    }

    @Test
    void updateDateOfLastVisitTest() throws ParseException {
        Member member1 = memberRepository.save(new Member("email1", "password1", Role.MEMBER));

        MoveStop moveStop1 = moveStopRepository.save(new MoveStop("test1", "address1", 0, 0));
        MoveStop moveStop2 = moveStopRepository.save(new MoveStop("test2", "address2", 0, 0));

        visitedMoveStopRepository.save(new VisitedMoveStop(member1, moveStop1, StringToLocalDateTime("2000-01-01T01:00:00")));
        visitedMoveStopRepository.save(new VisitedMoveStop(member1, moveStop2, StringToLocalDateTime("2000-01-01T01:10:00")));

        Optional<VisitedMoveStop> foundVisitedMoveStopOptional = visitedMoveStopRepository.findVisitedMoveStopByMemberIdAndMoveStopId(member1.getId(), moveStop1.getId());

        if (foundVisitedMoveStopOptional.isEmpty()) {
            Assertions.fail();
        }

        VisitedMoveStop foundVisitedMoveStop = foundVisitedMoveStopOptional.get();

        foundVisitedMoveStop.updateDateOfLastVisit(StringToLocalDateTime("2000-01-01T02:30:00"));

        // 재조회
        foundVisitedMoveStopOptional = visitedMoveStopRepository.findVisitedMoveStopByMemberIdAndMoveStopId(member1.getId(), moveStop1.getId());

        if (foundVisitedMoveStopOptional.isEmpty()) {
            Assertions.fail();
        }

        foundVisitedMoveStop = foundVisitedMoveStopOptional.get();

        assertThat(foundVisitedMoveStop.getDateOfLastVisit()).isEqualTo(StringToLocalDateTime("2000-01-01T02:30:00"));
    }

    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

    private LocalDateTime StringToLocalDateTime(String string) throws ParseException {
        return LocalDateTime.ofInstant(format.parse(string).toInstant(), ZoneId.systemDefault());
    }
}
