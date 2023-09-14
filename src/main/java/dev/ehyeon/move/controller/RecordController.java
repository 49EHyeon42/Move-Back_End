package dev.ehyeon.move.controller;

import dev.ehyeon.move.request.GetRecordRequest;
import dev.ehyeon.move.request.SaveRecordRequest;
import dev.ehyeon.move.response.GetRecordResponse;
import dev.ehyeon.move.service.RecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RecordController {

    private final RecordService recordService;

    @GetMapping("/api/record")
    public List<GetRecordResponse> getRecord(GetRecordRequest request) {
        return recordService.findAllRecordByMemberEmailAndSavedDateBetween(getMemberEmail(), request.getFrom(), request.getTo());
    }

    @PostMapping("/api/record")
    public void saveRecord(@RequestBody SaveRecordRequest request) {
        recordService.saveRecordByMemberEmail(getMemberEmail(), request);
    }

    private String getMemberEmail() {
        return (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
