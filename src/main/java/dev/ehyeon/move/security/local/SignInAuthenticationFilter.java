package dev.ehyeon.move.security.local;

import com.fasterxml.jackson.databind.ObjectMapper;
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
        // TODO catch MismatchedInputException, null etc
        SignInRequest signInRequest = objectMapper.readValue(request.getInputStream(), SignInRequest.class);

        return getAuthenticationManager().authenticate(new SignInAuthenticationToken(signInRequest.getEmail(), signInRequest.getPassword()));
    }
}
