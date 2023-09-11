package dev.ehyeon.move.security.local;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import dev.ehyeon.move.security.exception.IllegalArgumentAuthenticationException;
import dev.ehyeon.move.security.exception.SecurityErrorCode;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignInAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private final ObjectMapper objectMapper;

    public SignInAuthenticationFilter(RequestMatcher requiresAuthenticationRequestMatcher, AuthenticationManager authenticationManager, ObjectMapper objectMapper) {
        super(requiresAuthenticationRequestMatcher, authenticationManager);

        this.objectMapper = objectMapper;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException {
        // TODO 예외 핸들링 수정, AuthenticationEntryPoint에 예외가 전달되지 않음
        try {
            SignInRequest signInRequest = objectMapper.readValue(request.getInputStream(), SignInRequest.class);

            validateSignInRequest(signInRequest);

            return getAuthenticationManager().authenticate(new SignInAuthenticationToken(signInRequest.getEmail(), signInRequest.getPassword()));
        } catch (MismatchedInputException | IllegalArgumentException e) {
            throw new IllegalArgumentAuthenticationException(SecurityErrorCode.ILLEGAL_ARGUMENT.getMessage());
        }
    }

    private void validateSignInRequest(SignInRequest request) {
        if (validateString(request.getEmail()) || validateString(request.getPassword())) {
            throw new IllegalArgumentException();
        }
    }

    private boolean validateString(String string) {
        return string == null || string.isBlank();
    }
}
