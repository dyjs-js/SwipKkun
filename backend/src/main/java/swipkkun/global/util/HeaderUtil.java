package swipkkun.global.util;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

public class HeaderUtil {
    private final static String COOKIE_KEY_NAME = "refresh_token";

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
