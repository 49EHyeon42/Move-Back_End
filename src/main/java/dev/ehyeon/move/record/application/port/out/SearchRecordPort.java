package dev.ehyeon.move.record.application.port.out;

import dev.ehyeon.move.entity.Member;
import dev.ehyeon.move.record.domain.Record;

import java.time.LocalDateTime;
import java.util.List;

public interface SearchRecordPort {

    List<Record> searchRecordByMemberAndLocalDateTimeBetween(Member member, LocalDateTime from, LocalDateTime to);
}
