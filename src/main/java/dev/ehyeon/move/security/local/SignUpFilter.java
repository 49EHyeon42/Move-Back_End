package dev.ehyeon.move.security.local;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import dev.ehyeon.move.global.ErrorResponse;
import dev.ehyeon.move.security.exception.DuplicateEmailException;
import dev.ehyeon.move.security.service.SignService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignUpFilter extends GenericFilterBean {

    private final RequestMatcher requestMatcher;
    private final ObjectMapper objectMapper;
    private final SignService signService;

    public SignUpFilter(RequestMatcher requestMatcher, ObjectMapper objectMapper, SignService signService) {
        this.requestMatcher = requestMatcher;
        this.objectMapper = objectMapper;
        this.signService = signService;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        doFilter((HttpServletRequest) request, (HttpServletResponse) response, chain);
    }

    private void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        if (!requestMatcher.matches(request)) {
            chain.doFilter(request, response);
            return;
        }

        try {
            SignUpRequest signUpRequest = objectMapper.readValue(request.getInputStream(), SignUpRequest.class);

            validateSignUpRequest(signUpRequest);

            signService.signUp(signUpRequest);

            onSignUpSuccess(response);
        } catch (MismatchedInputException | IllegalArgumentException | DuplicateEmailException e) {
            onSignUpFailure(response, e);
        }
    }

    private void validateSignUpRequest(SignUpRequest request) {
        if (validateString(request.getEmail()) || validateString(request.getPassword())) {
            throw new IllegalArgumentException();
        }
    }

    private boolean validateString(String string) {
        return string == null || string.isBlank();
    }

    private void onSignUpSuccess(HttpServletResponse response) {
        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
    }

    private void onSignUpFailure(HttpServletResponse response, Exception exception) throws IOException {
        if (exception instanceof MismatchedInputException || exception instanceof IllegalArgumentException) {
            setResponse(response, HttpStatus.BAD_REQUEST, "ILLEGAL_ARGUMENT");
        } else if (exception instanceof DuplicateEmailException) {
            setResponse(response, HttpStatus.CONFLICT, exception.getMessage());
        } else {
            setResponse(response, HttpStatus.INTERNAL_SERVER_ERROR, "INTERNAL_SERVER_ERROR");
        }
    }

    private void setResponse(HttpServletResponse response, HttpStatus httpStatus, String message) throws IOException {
        response.setStatus(httpStatus.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write(objectMapper.writeValueAsString(
                new ErrorResponse(httpStatus.value() + " " + httpStatus.getReasonPhrase(), message)));
    }
}
