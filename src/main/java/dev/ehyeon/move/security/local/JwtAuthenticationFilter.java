package dev.ehyeon.move.security.local;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
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

    public JwtAuthenticationFilter(RequestMatcher requiresAuthenticationRequestMatcher, AuthenticationManager authenticationManager) {
        this.requiresAuthenticationRequestMatcher = requiresAuthenticationRequestMatcher;
        this.authenticationManager = authenticationManager;
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

        Authentication authenticate = authenticationManager.authenticate(new JwtAuthenticationToken(jwt));

        SecurityContextHolder.getContext().setAuthentication(authenticate);

        filterChain.doFilter(request, response);
    }

    private boolean requiresAuthentication(HttpServletRequest request) {
        return this.requiresAuthenticationRequestMatcher.matches(request);
    }
}
