package dev.ehyeon.move.security.local;

import dev.ehyeon.move.security.exception.MemberNotFoundException;
import dev.ehyeon.move.service.SignService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.http.HttpHeaders;
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

    private final RequestMatcher requestMatcher;
    private final SignService signService;

    public JwtAuthenticationFilter(RequestMatcher requestMatcher, SignService signService) {
        this.requestMatcher = requestMatcher;
        this.signService = signService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (!requestMatcher.matches(request)) {
            filterChain.doFilter(request, response);
            return;
        }

        String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (authorization == null || !authorization.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String jwt = authorization.substring(7);

        try {
            Authentication authentication = signService.authenticateMemberByJwt(jwt);

            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException |
                 SignatureException | IllegalArgumentException | MemberNotFoundException ignored) {
        }

        filterChain.doFilter(request, response);
    }
}
