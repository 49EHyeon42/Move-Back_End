package dev.ehyeon.move.security.local;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EmailPasswordAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private final ObjectMapper objectMapper;

    public EmailPasswordAuthenticationFilter(RequestMatcher requiresAuthenticationRequestMatcher, AuthenticationManager authenticationManager, ObjectMapper objectMapper) {
        super(requiresAuthenticationRequestMatcher, authenticationManager);

        this.objectMapper = objectMapper;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        SignInRequest signInRequest = objectMapper.readValue(request.getInputStream(), SignInRequest.class);

        return getAuthenticationManager().authenticate(new EmailPasswordAuthenticationToken(signInRequest.getEmail(), signInRequest.getPassword()));
    }
}
