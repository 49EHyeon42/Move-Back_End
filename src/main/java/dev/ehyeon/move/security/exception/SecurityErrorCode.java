package dev.ehyeon.move.security.exception;

public enum SecurityErrorCode {

    MEMBER_NOT_FOUND("MEMBER_NOT_FOUND"),
    EXPIRED_JWT("EXPIRED_JWT"),
    UNSUPPORTED_JWT("UNSUPPORTED_JWT"),
    MALFORMED_JWT("MALFORMED_JWT"),
    BAD_SIGNATURE("BAD_SIGNATURE"),
    ILLEGAL_ARGUMENT("ILLEGAL_ARGUMENT");

    private final String message;

    SecurityErrorCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
