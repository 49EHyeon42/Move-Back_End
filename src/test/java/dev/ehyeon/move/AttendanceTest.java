package dev.ehyeon.move;

import dev.ehyeon.move.entity.Attendance;
import dev.ehyeon.move.entity.Member;
import dev.ehyeon.move.entity.Role;
import dev.ehyeon.move.repository.AttendanceRepository;
import dev.ehyeon.move.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AttendanceTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    AttendanceRepository attendanceRepository;

    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

    @Test
    void existsAttendanceByMemberIdAndDateBetweenTest() throws ParseException {
        Member savedMember = memberRepository.save(new Member("email@domain.com", "password", Role.MEMBER));

        attendanceRepository.save(new Attendance(savedMember, StringToLocalDateTime("2000-01-01T01:23:45")));

        boolean result = attendanceRepository.existsAttendanceByMemberIdAndDateTimeBetween(
                savedMember.getId(), StringToLocalDateTime("2000-01-01T00:00:00"), StringToLocalDateTime("2000-01-01T23:59:59"));

        Assertions.assertThat(result).isTrue();

        result = attendanceRepository.existsAttendanceByMemberIdAndDateTimeBetween(
                savedMember.getId(), StringToLocalDateTime("2000-01-02T00:00:00"), StringToLocalDateTime("2000-01-02T23:59:59"));

        Assertions.assertThat(result).isFalse();
    }

    private LocalDateTime StringToLocalDateTime(String string) throws ParseException {
        return LocalDateTime.ofInstant(format.parse(string).toInstant(), ZoneId.systemDefault());
    }
}
