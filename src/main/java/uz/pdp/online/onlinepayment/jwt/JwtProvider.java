package uz.pdp.online.onlinepayment.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtProvider {

    @Value("${spring.security.jwt.login-remember-me-expiration-time-minute}")
    private int expirationWithRememberMeTimeMinute;

    @Value("${spring.security.jwt.login-without-remember-me-expiration-time-minute}")
    private int expirationWithOutRememberMeTimeMinute;

    @Value("${spring.security.jwt.secret-key}")
    private String SECRET_KEY;

    public Claims parse(String token) {
        return Jwts
                .parser()
                .verifyWith(getSignKey())
                .build()
                .parseSignedClaims(token).getPayload();
    }

    public String addBearerToken(String token) {
        return "Bearer " + token;
    }

    public String extractTokenFromHeader(String headerValue) {
        headerValue = headerValue.replace("Bearer ", "");
        return headerValue;
    }


    public String generateToken(String username, boolean rememberMe) {
        return generateToken(username, new HashMap<>(), rememberMe);
    }

    public String generateToken(String username, Map<String, Object> claims, boolean rememberMe) {
        int expiration;
        if (rememberMe) {
            expiration = expirationWithRememberMeTimeMinute;
        } else {
            expiration = expirationWithOutRememberMeTimeMinute;
        }
        return buildToken(username, claims, expiration);
    }

    private String buildToken(String username, Map<String, Object> claims, int expiration) {
        System.out.println("Generating JWT token3");
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + (long) expiration * 60 * 1000))
                .signWith(getSignKey())
                .compact();
    }

    private SecretKey getSignKey() {
        byte[] decode = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(decode);
    }


    public boolean isValid(Claims claims) {
        return claims.getExpiration().after(new Date());
    }

    public String getSubject(Claims claims) {
        return claims.getSubject();
    }

    public String generateTokenForSendingSms(Map<String, String> claims, int expiration) {
        return Jwts.builder()
                .signWith(getSignKey())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + (long) expiration * 60 * 1000))
                .claims(claims)
                .compact();
    }
}
