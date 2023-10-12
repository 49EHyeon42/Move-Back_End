package dev.ehyeon.move.record.adapter.in.web;

import dev.ehyeon.move.record.application.port.in.RegisterRecordRequest;
import dev.ehyeon.move.record.application.port.in.RegisterRecordUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class RegisterRecordController {

    private final RegisterRecordUseCase useCase;

    @PostMapping("/api/record")
    public void registerRecord(@RequestBody @Valid RegisterRecordRequest request) {
        useCase.registerRecord(getMemberEmail(), request);
    }

    private String getMemberEmail() {
        return (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
