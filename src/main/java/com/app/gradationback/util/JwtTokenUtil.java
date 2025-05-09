package com.app.gradationback.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class JwtTokenUtil {

    @Value("${jwt.secret}")
    private String secretKey;

//    토큰 생성 메서드
    public String generateToken(Map<String, Object> claims) {
        HashMap<String, Object> userInfo = new HashMap<String, Object>();
        String email = (String) claims.get("email"); // 이메일 추출
        String name = (String) claims.get("name"); // 이름 추출
        String identification = (String) claims.get("identification");

        userInfo.put("email", email);
        userInfo.put("name", name);
        userInfo.put("identification", identification);

//        24시간
        long expirationTimeInMillis = 24 * 60 * 60 * 1000;
        Date expirationDate = new Date(System.currentTimeMillis() + expirationTimeInMillis);

        return Jwts.builder()
                .claims(userInfo)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, secretKey) // sha-256
                .setHeaderParam("typ", "JWT")
                .compact();
    }

//    토큰 파싱 메서드
    public Claims parseToken(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

        } catch (ExpiredJwtException e) {
//            만료된 토큰 처리
            log.error("Expired JWT Token", e);
            throw new RuntimeException("Expired JWT Token");
        } catch (Exception e) {
//            토큰 정보 일치하지 않음
            log.error("Invalid JWT Token", e);
            throw new RuntimeException("Invalid JWT Token"); // 실제로는 프로젝트 exception으로 변경하기
        }
    }

//    JWT 토큰에서 이메일 추출
    public String getEmailFromToken(String token) {
        Claims claims = parseToken(token);
        return claims.get("email", String.class); // 이메일 클레임 가져오기
    }
//    JWT 토큰에서 이름을 추출
    public String getNameFromToken(String token) {
        Claims claims = parseToken(token);
        return claims.get("name", String.class); // 이름 클레임 가져오기
    }

//    JWT 토큰에서 이메일 추출
    public String getIdentificationFromToken(String token) {
        Claims claims = parseToken(token);
        return claims.get("identification", String.class);
    }

//    토큰이 유효한지 아닌지 검증
    public boolean isTokenValid(String token) {
        try {
            parseToken(token);
            return true;
        } catch (Exception e) {
            return false; // 유효하지 않는 토큰
        }
    }

}
