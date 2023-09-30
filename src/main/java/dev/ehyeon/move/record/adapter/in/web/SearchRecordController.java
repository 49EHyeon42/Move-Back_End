package dev.ehyeon.move.record.adapter.in.web;

import dev.ehyeon.move.record.application.port.in.SearchRecordRequest;
import dev.ehyeon.move.record.application.port.in.SearchRecordResponse;
import dev.ehyeon.move.record.application.port.in.SearchRecordUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SearchRecordController {

    private final SearchRecordUseCase useCase;

    @GetMapping("/api/record")
    public List<SearchRecordResponse> searchRecord(SearchRecordRequest request) {
        return useCase.searchRecordByMemberEmailAndLocalDateTimeBetween(getMemberEmail(), request.getFrom(), request.getTo());
    }

    private String getMemberEmail() {
        return (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
