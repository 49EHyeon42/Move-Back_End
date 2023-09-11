package dev.ehyeon.move.security.local;

import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class SignRequestValidation {

    private static final String EMAIL_PATTERN = "^[A-Za-z0-9+_.-]+@(.+)$";

    public boolean validateEmail(String email) {
        return validateString(email) && Pattern.matches(EMAIL_PATTERN, email);
    }

    public boolean validatePassword(String password) {
        return validateString(password);
    }

    private boolean validateString(String string) {
        return string != null && !string.isBlank();
    }
}
