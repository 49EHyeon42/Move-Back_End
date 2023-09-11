package dev.ehyeon.move.security.local;

import org.springframework.security.authentication.AbstractAuthenticationToken;

public class SignInAuthenticationToken extends AbstractAuthenticationToken {

    private final String principal;
    private final String credentials;


    public SignInAuthenticationToken(String jwt) {
        super(null);

        this.principal = jwt;
        credentials = null;
    }

    public SignInAuthenticationToken(String email, String password) {
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
