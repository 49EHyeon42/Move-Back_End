package dev.ehyeon.move.security.local;

import org.springframework.security.authentication.AbstractAuthenticationToken;

public class EmailPasswordAuthenticationToken extends AbstractAuthenticationToken {

    private final String principal;
    private final String credentials;


    public EmailPasswordAuthenticationToken(String jwt) {
        super(null);

        this.principal = jwt;
        credentials = null;
    }

    public EmailPasswordAuthenticationToken(String email, String password) {
        super(null);

        this.principal = email;
        this.credentials = password;
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
