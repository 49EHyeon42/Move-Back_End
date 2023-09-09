package dev.ehyeon.move.security.exception;

import org.springframework.security.core.AuthenticationException;

public class SignatureAuthenticationException extends AuthenticationException {

    public SignatureAuthenticationException(String msg) {
        super(msg);
    }

    public SignatureAuthenticationException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
