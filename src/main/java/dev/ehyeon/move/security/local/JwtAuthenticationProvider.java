package dev.ehyeon.move.security.local;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationProvider implements AuthenticationProvider {

    private final EmailPasswordUserDetailsService emailPasswordUserDetailsService;
    private final JwtProvider jwtProvider;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Claims claims = jwtProvider.getClaims((String) authentication.getPrincipal());

        String email = claims.get("jti", String.class);

        UserDetails userDetails = emailPasswordUserDetailsService.loadUserByUsername(email);

        JwtAuthenticationToken jwtAuthenticationToken = new JwtAuthenticationToken(userDetails.getUsername(), userDetails.getAuthorities());
        jwtAuthenticationToken.setAuthenticated(true);

        return jwtAuthenticationToken;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JwtAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
