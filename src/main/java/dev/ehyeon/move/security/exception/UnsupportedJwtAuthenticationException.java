package dev.ehyeon.move.security.exception;

import org.springframework.security.core.AuthenticationException;

public class UnsupportedJwtAuthenticationException extends AuthenticationException {

    public UnsupportedJwtAuthenticationException(String msg) {
        super(msg);
    }

    public UnsupportedJwtAuthenticationException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
