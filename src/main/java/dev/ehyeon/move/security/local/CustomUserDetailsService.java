package dev.ehyeon.move.security.local;

import dev.ehyeon.move.entity.Member;
import dev.ehyeon.move.repository.MemberRepository;
import dev.ehyeon.move.security.exception.SecurityErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member foundMember = memberRepository.findMemberByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException(SecurityErrorCode.MEMBER_NOT_FOUND.getMessage()));

        return new CustomUserDetails(foundMember.getEmail(), foundMember.getPassword(), List.of(new SimpleGrantedAuthority("ROLE_MEMBER")));
    }
}
