package dev.ehyeon.move.security.exception;

import org.springframework.security.core.AuthenticationException;

public class ExpiredJwtAuthenticationException extends AuthenticationException {

    public ExpiredJwtAuthenticationException(String msg) {
        super(msg);
    }

    public ExpiredJwtAuthenticationException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
