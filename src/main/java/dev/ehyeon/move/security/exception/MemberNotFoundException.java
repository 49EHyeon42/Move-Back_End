package dev.ehyeon.move.security.exception;

public class MemberNotFoundException extends RuntimeException {

    public MemberNotFoundException() {
        super("MEMBER_NOT_FOUND");
    }
}
