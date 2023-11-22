package org.project.backend.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
@Slf4j
public class JwtUtil {

    @Value("${jwt.secretKey}")
    private final String secretKey;

    @Value("${jwt.accessTokenTimeOut}")
    private final Integer accessTokenTimeOut;

    private final int timeOut;

    public JwtUtil(
            @Value("${jwt.secretKey}") String secretKey,
            @Value("${jwt.accessTokenTimeOut}") Integer accessTokenTimeOut
    ) {
        this.secretKey = secretKey;
        this.accessTokenTimeOut = accessTokenTimeOut;
        this.timeOut = accessTokenTimeOut * 60 * 1000;
    }

    public String generate(Long id) {
        Date now = new Date();
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, secretKey.getBytes(StandardCharsets.UTF_8))
                .setHeaderParam("typ", "ACCESS_TOKEN")
                .setHeaderParam("alg", "HS256")
                .setSubject(String.valueOf(id))
                .setExpiration(new Date(now.getTime() + timeOut))
                .setIssuedAt(now)
                .compact();
    }

    public void validate(String token) {
        try {
            Jwts.parser().setSigningKey(secretKey.getBytes(StandardCharsets.UTF_8)).parseClaimsJws(token);
        } catch (Exception e) {
            log.error("", e);
            log.warn("잘못된 토큰으로 요청: {}", token);
            throw e;
        }
    }

    public Long getId(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey.getBytes(StandardCharsets.UTF_8)).parseClaimsJws(token);
            return Long.valueOf(claims.getBody().getSubject());
        } catch (Exception e) {
            log.warn("잘못된 토큰으로 요청: {}", token);
            throw e;
        }
    }
}


