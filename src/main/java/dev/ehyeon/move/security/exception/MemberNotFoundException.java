package dev.ehyeon.move.security.exception;

public class MemberNotFoundException extends RuntimeException {

    public MemberNotFoundException() {
        super(SecurityErrorCode.MEMBER_NOT_FOUND.getMessage());
    }
}
