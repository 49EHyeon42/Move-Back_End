package dev.ehyeon.move.security.local;

import dev.ehyeon.move.security.exception.*;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtProvider {

    private static final String ISSUER = "Move";
    private static final int EXPIRATION_TIME = 1 * 60 * 1000;

    @Value("${jwt.private-key}")
    private String jwtPrivateKey;

    public String createJwt(String email) {
        Map<String, Object> header = new HashMap<>();
        header.put("typ", "JWT");
        header.put("alg", SignatureAlgorithm.HS256.getValue());

        Map<String, Object> claims = new HashMap<>();
        claims.put("iss", ISSUER);

        Date date = new Date();
        date.setTime(date.getTime() + EXPIRATION_TIME);
        claims.put("exp", date);

        claims.put("iat", new Date());

        claims.put("jti", email);

        return Jwts.builder()
                .setHeader(header)
                .setClaims(claims)
                .signWith(new SecretKeySpec(DatatypeConverter.parseBase64Binary(jwtPrivateKey), SignatureAlgorithm.HS256.getJcaName()), SignatureAlgorithm.HS256)
                .compact();
    }

    public Claims getClaims(String jwt) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(new SecretKeySpec(DatatypeConverter.parseBase64Binary(jwtPrivateKey), SignatureAlgorithm.HS256.getJcaName()))
                    .build()
                    .parseClaimsJws(jwt)
                    .getBody();
        } catch (ExpiredJwtException e) {
            throw new ExpiredJwtAuthenticationException("EXPIRED_JWT");
        } catch (UnsupportedJwtException e) {
            throw new UnsupportedJwtAuthenticationException("UNSUPPORTED_JWT");
        } catch (MalformedJwtException e) {
            throw new MalformedJwtAuthenticationException("MALFORMED_JWT");
        } catch (SignatureException e) {
            throw new SignatureAuthenticationException("BAD_SIGNATURE");
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentAuthenticationException("ILLEGAL_ARGUMENT");
        }
    }
}
