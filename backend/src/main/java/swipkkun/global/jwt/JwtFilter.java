package swipkkun.global.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import swipkkun.domain.member.exception.MemberException;

import java.io.IOException;

@RequiredArgsConstructor
@Slf4j
public class JwtFilter extends OncePerRequestFilter {
    private final JwtTokenProvider tokenProvider;
    private final String jwtKey;

    // 여기서 권한을 부여할 수 있다!!
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
        log.info("authorization : {}", authorization);

        // 토큰이 안 담겨 왔거나, 담겨 왔어도 Bearer로 시작하지 않는다면
        if (authorization == null || !authorization.startsWith("Bearer ")) {
            log.error("authorization을 잘못 보냈습니다");
            filterChain.doFilter(request, response);
            return;
        }

        String accessToken = authorization.split(" ")[1];
        tokenProvider.validateToken(accessToken, jwtKey);

        Authentication authentication = tokenProvider.getAuthentication(accessToken, jwtKey);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
        // request에 인증됐다고 도장이 찍히고 통과가 된다
    }
}
