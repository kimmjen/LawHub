package com.example.lawhubbackend.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.List;

@Component
public class JwtTokenProvider {

    // 외부 설정 파일에서 비밀 키를 불러옴 (Base64로 인코딩된 문자열을 사용)
    @Value("${jwt.secret-key}")
    private String secretKeyString;

    // 외부 설정 파일에서 토큰 만료 시간 불러오기
    @Value("${jwt.expiration-time}")
    private long validityInMilliseconds;

    // 비밀 키를 SecretKey로 변환
    private SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(secretKeyString.getBytes());
    }

    // JWT 토큰 생성 메서드
    public String createToken(String username, String role) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds); // 외부 설정에서 가져온 유효 시간 사용

        return Jwts.builder()
                .setSubject(username)
                .claim("role", role) // 사용자 권한을 클레임으로 추가
                .setIssuedAt(now)
                .setExpiration(validity) // 토큰 만료 시간 설정
                .signWith(getSecretKey(), SignatureAlgorithm.HS256) // SecretKey로 서명
                .compact(); // 토큰 생성 및 반환
    }

    // 토큰 유효성 검증 메서드
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(getSecretKey()).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // 토큰에서 사용자 이름 추출
    public String getUsername(String token) {
        Claims claims = Jwts.parserBuilder().setSigningKey(getSecretKey()).build().parseClaimsJws(token).getBody();
        return claims.getSubject();
    }
}