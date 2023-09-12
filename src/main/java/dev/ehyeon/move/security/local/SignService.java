package dev.ehyeon.move.security.local;

import dev.ehyeon.move.entity.Member;
import dev.ehyeon.move.entity.Role;
import dev.ehyeon.move.repository.MemberRepository;
import dev.ehyeon.move.security.exception.DuplicateEmailException;
import dev.ehyeon.move.security.exception.MemberNotFoundException;
import dev.ehyeon.move.security.local.jwt.JwtAuthenticationToken;
import dev.ehyeon.move.security.local.signin.SignInRequest;
import dev.ehyeon.move.security.local.signup.SignUpRequest;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SignService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    public String signIn(SignInRequest request) {
        Member foundMember = memberRepository.findMemberByEmail(request.getEmail()).orElseThrow(MemberNotFoundException::new);

        if (!passwordEncoder.matches(request.getPassword(), foundMember.getPassword())) {
            throw new MemberNotFoundException();
        }

        return jwtProvider.createJwt(foundMember.getEmail());
    }

    public void signUp(SignUpRequest request) {
        if (memberRepository.existsMemberByEmail(request.getEmail())) {
            throw new DuplicateEmailException();
        }

        memberRepository.save(new Member(request.getEmail(), passwordEncoder.encode(request.getPassword()), Role.MEMBER));
    }

    public Authentication authenticateMemberByJwt(String jwt) {
        Claims claims = jwtProvider.getClaims(jwt);

        String email = claims.get("jti", String.class);

        Member foundMember = memberRepository.findMemberByEmail(email).orElseThrow(MemberNotFoundException::new);

        return new JwtAuthenticationToken(foundMember.getEmail(), List.of(new SimpleGrantedAuthority(foundMember.getRole().getRole())));
    }
}
