package uz.pdp.online.onlinepayment.common.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {

    @Value("${spring.security.jwt.login-remember-me-expiration-time-minute}")
    private int expirationWithRememberMeTimeMinute;

    @Value("${spring.security.jwt.login-without-remember-me-expiration-time-minute}")
    private int expirationWithOutRememberMeTimeMinute;

    @Value("${spring.security.jwt.secret-key}")
    private String SECRET_KEY;

    public String generateToken(String username, boolean rememberMe) {
        System.out.println("Generating JWT token1");
        return generateToken(username, new HashMap<>(), rememberMe);
    }

    public String generateToken(String username, Map<String, Object> claims, boolean rememberMe) {
        System.out.println("Generating JWT token2");
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

}
