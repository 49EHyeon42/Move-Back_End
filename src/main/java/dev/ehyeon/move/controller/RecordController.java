package dev.ehyeon.move.controller;

import dev.ehyeon.move.request.SaveRecordRequest;
import dev.ehyeon.move.service.RecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RecordController {

    private final RecordService recordService;

    @PostMapping("/api/record")
    public void saveRecord(@RequestBody SaveRecordRequest request) {
        recordService.saveRecordByMemberEmail(getMemberEmail(), request);
    }

    private String getMemberEmail() {
        return (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
