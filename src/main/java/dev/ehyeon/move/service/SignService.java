package dev.ehyeon.move.service;

import dev.ehyeon.move.entity.Member;
import dev.ehyeon.move.repository.MemberRepository;
import dev.ehyeon.move.request.SignUpRequest;
import dev.ehyeon.move.security.exception.DuplicateEmailException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SignService {

    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;

    public void signUp(SignUpRequest request) {
        if (memberRepository.existsMemberByEmail(request.getEmail())) {
            throw new DuplicateEmailException("DUPLICATE_EMAIL");
        }

        memberRepository.save(new Member(request.getEmail(), passwordEncoder.encode(request.getPassword())));
    }
}
