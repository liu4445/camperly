package org.project.backend.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class JwtUtilTest {

    private final String secretKey = "test";
    private final int accessTokenTimeOut = 60;
    private final JwtUtil jwtUtil = new JwtUtil(secretKey, accessTokenTimeOut);

    @Test
    @DisplayName("JwtUtil 생성을 테스트한다")
    void JwtUtil_생성을_테스트한다() {
        assertThat(jwtUtil).isNotNull();
    }

    @ParameterizedTest
    @DisplayName("Jwt 토큰 발급을 테스트한다")
    @ValueSource(longs = {1, 100, Long.MAX_VALUE})
    void Jwt_토큰_발급을_테스트한다(long id) {
        // given

        // when
        String token = jwtUtil.generate(id);

        // then
        Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
        assertThat(claims.getSubject()).isEqualTo(String.valueOf(id));
    }

    @Nested
    @DisplayName("Jwt 토큰에서 ID를 추출할 때")
    class Jwt_토큰에서_ID를_추출할_때 {
        @ParameterizedTest
        @DisplayName("정상 토큰이면 ID 추출이 정상적으로 동작한다")
        @ValueSource(longs = {1, 100, Long.MAX_VALUE})
        void 정상_토큰이면_ID_추출이_정상적으로_동작한다(long id) {
            // given
            Date now = new Date();
            String token = Jwts.builder()
                    .signWith(SignatureAlgorithm.HS256, secretKey)
                    .setHeaderParam("typ", "ACCESS_TOKEN")
                    .setHeaderParam("alg", "HS256")
                    .setSubject(String.valueOf(id))
                    .setExpiration(new Date(now.getTime() + 60 * 60 * accessTokenTimeOut))
                    .setIssuedAt(now)
                    .compact();

            // when
            Long findId = jwtUtil.getId(token);

            // then
            assertThat(findId).isEqualTo(id);
        }

        @ParameterizedTest
        @ValueSource(longs = {1, 100, Long.MAX_VALUE})
        @DisplayName("비정상 토큰이면 예외를 발생한다")
        void 비정상_토큰이면_예외를_발생한다(long id) {
            // given
            Date now = new Date();
            String token = Jwts.builder()
                    .signWith(SignatureAlgorithm.HS256, secretKey)
                    .setHeaderParam("typ", "ACCESS_TOKEN")
                    .setHeaderParam("alg", "HS256")
                    .setSubject(String.valueOf(id))
                    .setExpiration(new Date(now.getTime()))
                    .setIssuedAt(now)
                    .compact();

            // when, then
            assertThatThrownBy(() -> jwtUtil.getId(token)).isInstanceOf(Exception.class);
        }
    }


}