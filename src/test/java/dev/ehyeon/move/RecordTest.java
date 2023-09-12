package dev.ehyeon.move;

import dev.ehyeon.move.entity.Member;
import dev.ehyeon.move.entity.Record;
import dev.ehyeon.move.entity.Role;
import dev.ehyeon.move.repository.MemberRepository;
import dev.ehyeon.move.repository.RecordRepository;
import dev.ehyeon.move.security.exception.MemberNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class RecordTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    RecordRepository recordRepository;

    @BeforeEach
    void before() throws ParseException {
        Member savedMember = memberRepository.save(new Member("email@domain.com", "password", Role.MEMBER));

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        recordRepository.save(
                new Record(savedMember, format.parse("2000-01-01 01:00:00"), 1, 1, 1, 1, 1));

        recordRepository.save(
                new Record(savedMember, format.parse("2000-01-01 02:00:00"), 2, 2, 2, 2, 2));

        recordRepository.save(
                new Record(savedMember, format.parse("2000-01-01 03:00:00"), 3, 3, 3, 3, 3));
    }

    @Test
    void saveRecordByMember() {
        // authentication -> jwt to email
        Member foundMember = memberRepository.findMemberByEmail("email@domain.com")
                .orElseThrow(MemberNotFoundException::new);

        recordRepository.save(
                new Record(foundMember, new Date(), 1, 1, 1, 1, 1));

        // findAllRecordByMember
        List<Record> foundRecords = recordRepository.findAllRecordByMemberId(foundMember.getId());

        for (Record foundRecord : foundRecords) {
            System.out.println("found record: id = " + foundRecord.getId());
        }
    }

    @Test
    void findAllRecordByMemberIdAndDateBetweenTest() throws ParseException {
        Member foundMember = memberRepository.findMemberByEmail("email@domain.com")
                .orElseThrow(MemberNotFoundException::new);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        List<Record> foundRecords = recordRepository.findAllRecordByMemberIdAndSavedDateBetween(
                foundMember.getId(),
                format.parse("2000-01-01 01:00:00"), format.parse("2000-01-01 02:00:00"));

        for (Record foundRecord : foundRecords) {
            System.out.println("found record: date = " + foundRecord.getSavedDate());
        }
    }
}
