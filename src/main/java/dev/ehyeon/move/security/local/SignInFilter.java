package dev.ehyeon.move.security.local;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import dev.ehyeon.move.global.ErrorResponse;
import dev.ehyeon.move.security.exception.MemberNotFoundException;
import dev.ehyeon.move.security.service.SignService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class SignInFilter extends GenericFilterBean {

    private final RequestMatcher requestMatcher = new AntPathRequestMatcher("/api/signin", HttpMethod.POST.name());
    private final ObjectMapper objectMapper;
    private final SignService signService;

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
            SignInRequest signInRequest = objectMapper.readValue(request.getInputStream(), SignInRequest.class);

            validateSignInRequest(signInRequest);

            String jwt = signService.signIn(signInRequest);

            onSignInSuccess(response, jwt);
        } catch (MismatchedInputException | IllegalArgumentException | MemberNotFoundException exception) {
            onSignInFailure(response, exception);
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

    private void onSignInSuccess(HttpServletResponse response, String jwt) throws IOException {
        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write(objectMapper.writeValueAsString(new SignInResponse(jwt)));
    }

    private void onSignInFailure(HttpServletResponse response, Exception exception) throws IOException {
        if (exception instanceof MismatchedInputException || exception instanceof IllegalArgumentException) {
            setResponse(response, HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.getReasonPhrase());
        } else if (exception instanceof MemberNotFoundException) {
            setResponse(response, HttpStatus.UNAUTHORIZED, exception.getMessage());
        } else {
            setResponse(response, HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        }
    }

    private void setResponse(HttpServletResponse response, HttpStatus httpStatus, String message) throws IOException {
        response.setStatus(httpStatus.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write(objectMapper.writeValueAsString(
                new ErrorResponse(httpStatus.value() + " " + httpStatus.getReasonPhrase(), message)));
    }
}
