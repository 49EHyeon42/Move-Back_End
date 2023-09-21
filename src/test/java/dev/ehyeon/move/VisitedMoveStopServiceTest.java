package dev.ehyeon.move;

import dev.ehyeon.move.entity.Member;
import dev.ehyeon.move.entity.MoveStop;
import dev.ehyeon.move.entity.Role;
import dev.ehyeon.move.repository.MemberRepository;
import dev.ehyeon.move.repository.MoveStopRepository;
import dev.ehyeon.move.request.VisitedMoveStopRequest;
import dev.ehyeon.move.service.VisitedMoveStopService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class VisitedMoveStopServiceTest {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    MoveStopRepository moveStopRepository;

    @Autowired
    VisitedMoveStopService visitedMoveStopService;

    @Test
    void saveOrUpdateVisitedMoveStopTest() {
        Member savedMember = memberRepository.save(new Member("email", "password", Role.MEMBER));

        moveStopRepository.save(new MoveStop("교통대 - 휴카페", "충청북도 충주시 대소원면", 36.96982982666459, 127.87093125625782));
        moveStopRepository.save(new MoveStop("교통대 - 중안정보관", "충청북도 충주시 대소원면", 36.969082343166534, 127.86964818301132));

        // jwt to email
        visitedMoveStopService.saveOrUpdateVisitedMoveStop(new VisitedMoveStopRequest(savedMember.getEmail(), "충청북도 충주시 대소원면", 36.96975984814846, 127.8706441022571));

        visitedMoveStopService.saveOrUpdateVisitedMoveStop(new VisitedMoveStopRequest(savedMember.getEmail(), "충청북도 충주시 대소원면", 36.96969952125424, 127.87119086128935));
    }
}
