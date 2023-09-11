package dev.ehyeon.move.security.exception;

public enum SecurityErrorCode {

    DUPLICATE_EMAIL("Duplicate Email"),
    MEMBER_NOT_FOUND("Member Not Found");

    private final String message;

    SecurityErrorCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
