package swipkkun.global.jwt;

import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;
import java.util.Date;

@Component
public class JwtTokenProvider {
    public String createToken(String userEmail, String jwtSecretKey, long expiredMs) {
        // claims : jwt에서 내가 원하는 걸 담는 공간. payload라고 보면 됨. 일종의 map
        Claims claims = Jwts.claims();
        claims.put("email", userEmail);

        return Jwts.builder()
                .setClaims(claims) // 위에서 만들어둔 claims 넣기
                .setIssuedAt(new Date(System.currentTimeMillis())) // 현재 시간 넣기
                .setExpiration(new Date(System.currentTimeMillis() + expiredMs)) // 종료시간 넣기
                .signWith(SignatureAlgorithm.HS256, jwtSecretKey) // 서명하기
                .compact();
    }

    public void validateToken(String token, String jwtSecretKey) {
        try {
            // 토큰 복호화
            Claims claims = Jwts.parser() // parser 생성
                    .setSigningKey(jwtSecretKey) //  JWS 디지털 서명을 확인하는 데 쓰일 키를 세팅
                    .parseClaimsJws(token)
                    .getBody();
        } catch (SignatureException e) {
            throw new IllegalArgumentException("토큰을 만들 때 쓰인 키가 아닙니다");
        } catch (ExpiredJwtException e) {
            throw new IllegalArgumentException("토큰의 만료기간이 지났습니다");
        }
    }

    public String getEmailFromToken(String token, String jwtSecretKey) {
        return Jwts.parser().setSigningKey(jwtSecretKey).parseClaimsJws(token)
                .getBody().get("email", String.class);
    }
}
