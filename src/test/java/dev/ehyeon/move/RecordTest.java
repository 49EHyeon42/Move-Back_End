package dev.ehyeon.move;

import dev.ehyeon.move.entity.Member;
import dev.ehyeon.move.entity.Record;
import dev.ehyeon.move.entity.Role;
import dev.ehyeon.move.repository.MemberRepository;
import dev.ehyeon.move.repository.RecordRepository;
import dev.ehyeon.move.security.exception.MemberNotFoundException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class RecordTest {

    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    RecordRepository recordRepository;

    @BeforeEach
    void before() throws ParseException {
        Member savedMember = memberRepository.save(new Member("email@domain.com", "password", Role.MEMBER));

        recordRepository.save(
                new Record(savedMember, StringToLocalDateTime("2000-01-01T01:00:00"), 1, 1, 1, 1, 1));

        recordRepository.save(
                new Record(savedMember, StringToLocalDateTime("2000-01-01T02:00:00"), 2, 2, 2, 2, 2));

        recordRepository.save(
                new Record(savedMember, StringToLocalDateTime("2000-01-01T03:00:00"), 3, 3, 3, 3, 3));
    }

    @Test
    void findAllRecordByMemberIdAndDateBetweenTest() throws ParseException {
        Member foundMember = memberRepository.findMemberByEmail("email@domain.com")
                .orElseThrow(MemberNotFoundException::new);

        List<Record> foundRecords = recordRepository.findAllRecordByMemberIdAndSavedDateBetween(
                foundMember.getId(), StringToLocalDateTime("2000-01-01T01:00:00"), StringToLocalDateTime("2000-01-01T02:00:00"));

        for (Record foundRecord : foundRecords) {
            System.out.println("found record: date = " + foundRecord.getSavedDate());
        }
    }

    @Test
    void existsAttendanceByMemberIdAndAttendanceDateBetweenTest() throws ParseException {
        Member foundMember = memberRepository.findMemberByEmail("email@domain.com")
                .orElseThrow(MemberNotFoundException::new);

        boolean result = recordRepository.existsRecordByMemberIdAndSavedDateBetween(
                foundMember.getId(), StringToLocalDateTime("2000-01-01T00:00:00"), StringToLocalDateTime("2000-01-01T23:59:59"));

        Assertions.assertThat(result).isTrue();

        result = recordRepository.existsRecordByMemberIdAndSavedDateBetween(
                foundMember.getId(), StringToLocalDateTime("2000-12-01T00:00:00"), StringToLocalDateTime("2000-12-01T23:59:59"));

        Assertions.assertThat(result).isFalse();
    }

    private LocalDateTime StringToLocalDateTime(String string) throws ParseException {
        return LocalDateTime.ofInstant(format.parse(string).toInstant(), ZoneId.systemDefault());
    }
}
