package swipkkun.global.jwt;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
class JwtTokenProviderTest {
    private final String jwtTestKey = "89A2CDACB6F1AD6B6A6184A31E6B7E37DB3818CC36871E26235DD67DCFE4041492";
    @Autowired private JwtTokenProvider tokenProvider;

    @DisplayName("토큰 검증 성공 테스트")
    @Test
    void test1() {
        long expiredMs = 30 * 60 * 1000L;
        String jwtToken = tokenProvider.createToken("test1@google.com", jwtTestKey, expiredMs);

        assertThatCode(() -> tokenProvider.validateToken(jwtToken, jwtTestKey))
                .doesNotThrowAnyException();
    }

    @DisplayName("토큰 검증 실패 - 생성한 키와 다른 키로 검증할 때")
    @Test
    void test2() {
        String anotherJwtTestKey = "11A2CDACB6F1AD6B6A6184A31E6B7E37DB3818CC36871E26235DD67DCFE4041492";
        long expiredMs = 30 * 60 * 1000L;
        String jwtToken = tokenProvider.createToken("test1@google.com", jwtTestKey, expiredMs);

        assertThatThrownBy(() -> tokenProvider.validateToken(jwtToken, anotherJwtTestKey))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("토큰 검증 실패 - 유효 기간 만료")
    @Test
    void test3() {
        long expiredMs = 0L;
        String jwtToken = tokenProvider.createToken("test1@google.com", jwtTestKey, expiredMs);
        assertThatThrownBy(() -> tokenProvider.validateToken(jwtToken, jwtTestKey))
                .isInstanceOf(IllegalArgumentException.class);
    }

}