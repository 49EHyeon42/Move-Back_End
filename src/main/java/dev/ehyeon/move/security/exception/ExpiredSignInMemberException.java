package dev.ehyeon.move.security.exception;

public class ExpiredSignInMemberException extends RuntimeException {

    public ExpiredSignInMemberException() {
        super(SecurityErrorCode.EXPIRED_SIGN_IN_MEMBER.getMessage());
    }
}
