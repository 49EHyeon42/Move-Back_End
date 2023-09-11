package dev.ehyeon.move.security.exception;

public class DuplicateEmailException extends RuntimeException {

    public DuplicateEmailException() {
        super(SecurityErrorCode.DUPLICATE_EMAIL.getMessage());
    }
}
