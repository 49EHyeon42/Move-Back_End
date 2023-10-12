package dev.ehyeon.move.repository;

import dev.ehyeon.move.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    boolean existsAttendanceByMemberIdAndDateTimeBetween(long memberId, LocalDateTime from, LocalDateTime to);
}
