package dev.ehyeon.move.security.local;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtProvider {

    @Value("${jwt.issuer}")
    private String issuer;

    @Value("${jwt.expiration-time}")
    private int expirationTime;

    @Value("${jwt.private-key}")
    private String jwtPrivateKey;

    public String createJwt(String email) {
        Map<String, Object> header = new HashMap<>();
        header.put("typ", "JWT");
        header.put("alg", SignatureAlgorithm.HS256.getValue());

        Map<String, Object> claims = new HashMap<>();
        claims.put("iss", issuer);

        Date date = new Date();
        date.setTime(date.getTime() + expirationTime);
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
        return Jwts.parserBuilder()
                .setSigningKey(new SecretKeySpec(DatatypeConverter.parseBase64Binary(jwtPrivateKey), SignatureAlgorithm.HS256.getJcaName()))
                .build()
                .parseClaimsJws(jwt)
                .getBody();
    }
}
