package dev.ehyeon.move.controller;

import dev.ehyeon.move.request.SaveRecordRequest;
import dev.ehyeon.move.response.GetRecordResponse;
import dev.ehyeon.move.service.RecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class RecordController {

    private final RecordService recordService;

    @GetMapping("/api/record")
    public List<GetRecordResponse> getRecord(@RequestParam("from") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime from,
                                             @RequestParam("to") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime to) {
        return recordService.findAllRecordByMemberEmailAndSavedDateBetween(getMemberEmail(), from, to);
    }

    @PostMapping("/api/record")
    public void saveRecord(@RequestBody SaveRecordRequest request) {
        recordService.saveRecordByMemberEmail(getMemberEmail(), request);
    }

    private String getMemberEmail() {
        return (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
