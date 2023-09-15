package dev.ehyeon.move.repository;

import dev.ehyeon.move.entity.Record;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface RecordRepository extends JpaRepository<Record, Long> {

    boolean existsRecordByMemberIdAndSavedDateBetween(long memberId, LocalDateTime from, LocalDateTime to);

    List<Record> findAllRecordByMemberId(long memberId);

    List<Record> findAllRecordByMemberIdAndSavedDateBetween(long memberId, LocalDateTime from, LocalDateTime to);
}
