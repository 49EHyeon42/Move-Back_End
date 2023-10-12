package dev.ehyeon.move.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/")
    public String root() {
        return "인증 성공";
    }

    @GetMapping("/api/hello")
    public String hello() {
        return "Hello, " + SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
