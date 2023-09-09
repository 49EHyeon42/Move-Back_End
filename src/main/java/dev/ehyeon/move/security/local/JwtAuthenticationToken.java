package dev.ehyeon.move.security.local;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class JwtAuthenticationToken extends AbstractAuthenticationToken {

    private final String principal;
    private final String credentials;

    public JwtAuthenticationToken(String jwt) {
        super(null);

        principal = jwt;
        credentials = null;
    }

    public JwtAuthenticationToken(String email, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);

        principal = email;
        credentials = null;
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }

    @Override
    public Object getCredentials() {
        return credentials;
    }
}
