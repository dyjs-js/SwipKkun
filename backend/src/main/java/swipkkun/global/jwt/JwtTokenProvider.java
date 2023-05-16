package swipkkun.global.jwt;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import swipkkun.domain.member.exception.ErrorCode;
import swipkkun.domain.member.exception.MemberException;
import swipkkun.domain.member.repository.MemberRepository;

import java.util.Date;
import java.util.stream.Collectors;

@Component
@Slf4j
public class JwtTokenProvider {
    private final MemberRepository memberRepository;

    public JwtTokenProvider(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public String createAccessToken(Authentication authentication, String jwtSecretKey, long expiredMs) {
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining());

        // claims : jwt에서 내가 원하는 걸 담는 공간. payload라고 보면 됨. 일종의 map
        Claims claims = Jwts.claims();
        claims.put("role", authorities);


        return Jwts.builder()
                .setClaims(claims) // 위에서 만들어둔 claims 넣기
                .setSubject(authentication.getName()) // 클레임 먼저 넣고 서브젝트 넣어야 함..클레임 넣는 작업이 페이로드 초기화하는거라
                .setIssuedAt(new Date(System.currentTimeMillis())) // 현재 시간 넣기
                .setExpiration(new Date(System.currentTimeMillis() + expiredMs)) // 종료시간 넣기
                .signWith(SignatureAlgorithm.HS256, jwtSecretKey) // 서명하기
                .compact();
    }

    public String createRefreshToken(String jwtSecretKey, long expiredMs) {
        return Jwts.builder()
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
            throw new SignatureException("토큰을 만들 때 쓰인 키가 아닙니다");
        } catch (ExpiredJwtException e) {
            throw new MemberException(ErrorCode.TOKEN_EXPIRED, "토큰의 만료기간이 지났습니다");
        }
    }

    public Authentication getAuthentication(String accessToken, String jwtKey) {
        String email = getEmailFromToken(accessToken, jwtKey);
        UserDetails userDetails = memberRepository.findByEmail(email).get();
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    private String getEmailFromToken(String accessToken, String jwtKey) {
        return Jwts.parser()
                .setSigningKey(jwtKey)
                .parseClaimsJws(accessToken)
                .getBody()
                .getSubject();
    }
}
