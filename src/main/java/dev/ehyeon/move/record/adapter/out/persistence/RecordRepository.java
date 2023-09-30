package dev.ehyeon.move.record.adapter.out.persistence;

import dev.ehyeon.move.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface RecordRepository extends JpaRepository<RecordEntity, Long> {

    List<RecordEntity> findAllRecordByMemberAndSavedDateBetween(Member member, LocalDateTime from, LocalDateTime to);
}
