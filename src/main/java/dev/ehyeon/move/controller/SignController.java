package dev.ehyeon.move.controller;

import dev.ehyeon.move.security.local.SignUpRequest;
import dev.ehyeon.move.security.service.SignService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SignController {

    private final SignService signService;

    @PostMapping("/api/signup")
    public String signUp(@RequestBody SignUpRequest request) {
        signService.signUp(request);

        return "OK";
    }
}
