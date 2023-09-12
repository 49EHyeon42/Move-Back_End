package dev.ehyeon.move.repository;

import dev.ehyeon.move.entity.Record;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface RecordRepository extends JpaRepository<Record, Long> {

    List<Record> findAllRecordByMemberId(long memberId);

    List<Record> findAllRecordByMemberIdAndSavedDateBetween(long memberId, Date from, Date to);
}
