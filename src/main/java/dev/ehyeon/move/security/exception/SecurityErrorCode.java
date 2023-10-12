package dev.ehyeon.move.security.exception;

public enum SecurityErrorCode {

    DUPLICATE_EMAIL("Duplicate Email"),
    EXPIRED_SIGN_IN_MEMBER("Expired Sign In Member"),
    MEMBER_NOT_FOUND("Member Not Found");

    private final String message;

    SecurityErrorCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
