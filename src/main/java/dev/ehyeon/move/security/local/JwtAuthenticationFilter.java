package dev.ehyeon.move.security.local;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.ehyeon.move.global.ErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final RequestMatcher requiresAuthenticationRequestMatcher;
    private final AuthenticationManager authenticationManager;
    private final ObjectMapper objectMapper;

    public JwtAuthenticationFilter(RequestMatcher requiresAuthenticationRequestMatcher, AuthenticationManager authenticationManager, ObjectMapper objectMapper) {
        this.requiresAuthenticationRequestMatcher = requiresAuthenticationRequestMatcher;
        this.authenticationManager = authenticationManager;
        this.objectMapper = objectMapper;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (!requiresAuthentication(request)) {
            filterChain.doFilter(request, response);
            return;
        }

        String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (authorization == null || !authorization.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String jwt = authorization.substring(7);

        // TODO CustomAuthenticationEntryPoint에 위임, 방법 못 찾음
        try {
            Authentication authenticate = authenticationManager.authenticate(new JwtAuthenticationToken(jwt));

            SecurityContextHolder.getContext().setAuthentication(authenticate);
        } catch (AuthenticationException e) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.getWriter().write(objectMapper.writeValueAsString(
                    new ErrorResponse(
                            HttpStatus.UNAUTHORIZED.value() + " " + HttpStatus.UNAUTHORIZED.getReasonPhrase(),
                            e.getMessage())));
            return;
        }

        filterChain.doFilter(request, response);
    }

    private boolean requiresAuthentication(HttpServletRequest request) {
        return this.requiresAuthenticationRequestMatcher.matches(request);
    }
}
