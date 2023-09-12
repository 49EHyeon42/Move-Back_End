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
    void before() {
        memberRepository.save(new Member("email@domain.com", "password", Role.MEMBER));
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
}
