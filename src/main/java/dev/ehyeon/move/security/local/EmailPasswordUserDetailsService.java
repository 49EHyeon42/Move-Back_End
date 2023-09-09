package dev.ehyeon.move.security.local;

import dev.ehyeon.move.entity.Member;
import dev.ehyeon.move.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class EmailPasswordUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member foundMember = memberRepository.findMemberByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("MEMBER_NOT_FOUND"));

        return new EmailPasswordUserDetails(foundMember.getEmail(), foundMember.getPassword(), List.of(new SimpleGrantedAuthority("ROLE_MEMBER")));
    }
}
