package swipkkun.global.util;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;

import java.util.Arrays;

public class HeaderUtil {
    private final static String COOKIE_KEY_NAME = "refresh_token";
    private final static String TOKEN_PREFIX = "Bearer ";


    public static String getAccessToken(HttpServletRequest request) {
        String headerValue = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (headerValue == null) {
            return null;
        }

        if (headerValue.startsWith(TOKEN_PREFIX)) {
            return headerValue.substring(TOKEN_PREFIX.length());
        }

        return null;
    }

    public static String getRefreshToken(HttpServletRequest request) {
        Cookie cookie = Arrays.stream(request.getCookies())
                .filter(element -> element.getName().equals(COOKIE_KEY_NAME))
                .findFirst()
                .orElse(null);

        if (cookie == null) {
            return null;
        }
        return cookie.getValue();
    }

    public static void setRefreshToken(HttpServletResponse response, String refreshToken) {
        Cookie cookie = new Cookie(COOKIE_KEY_NAME, refreshToken);
        // 1시간 뒤 만료되게끔
        cookie.setMaxAge(60 * 60);
        // js로 쿠키 조회 막기
        cookie.setHttpOnly(true);
        // 이 하위로는 다 사용 가능하다. 즉 모든 경로에 대해 적용
        cookie.setPath("/");

        response.addCookie(cookie);
    }
}
