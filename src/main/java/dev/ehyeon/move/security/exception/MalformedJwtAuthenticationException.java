package dev.ehyeon.move.security.exception;

import org.springframework.security.core.AuthenticationException;

public class MalformedJwtAuthenticationException extends AuthenticationException {

    public MalformedJwtAuthenticationException(String msg) {
        super(msg);
    }

    public MalformedJwtAuthenticationException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
