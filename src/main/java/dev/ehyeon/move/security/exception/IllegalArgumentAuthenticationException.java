package dev.ehyeon.move.security.exception;

import org.springframework.security.core.AuthenticationException;

public class IllegalArgumentAuthenticationException extends AuthenticationException {

    public IllegalArgumentAuthenticationException(String msg) {
        super(msg);
    }

    public IllegalArgumentAuthenticationException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
