package dev.ehyeon.move.record.application.port.in;

import java.time.LocalDateTime;
import java.util.List;

public interface SearchRecordUseCase {

    List<SearchRecordResponse> searchRecordByMemberEmailAndLocalDateTimeBetween(String memberEmail, LocalDateTime from, LocalDateTime to);
}
