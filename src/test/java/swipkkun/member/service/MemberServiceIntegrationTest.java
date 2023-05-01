package swipkkun.member.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import swipkkun.member.domain.Member;
import swipkkun.member.dto.LoginRequestDto;
import swipkkun.member.dto.SignupRequestDto;
import swipkkun.member.repository.MemberRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {
    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Nested
    class SignupTest {
        @DisplayName("회원가입 테스트")
        @Test
        void signup() {
            SignupRequestDto requestDto = new SignupRequestDto();
            requestDto.setEmail("test@google.com");
            requestDto.setPassword("123213");
            requestDto.setNickname("구우욷");
            requestDto.setPhone("010-2134-4321");

            memberService.signup(requestDto);
            Member joinedMember = memberService.findByEmail("test@google.com");

            assertThat(joinedMember.getEmail()).isEqualTo(requestDto.getEmail());
        }
    }

    @Nested
    class LoginTest {
        @DisplayName("로그인 테스트")
        @Test
        void login() {
            SignupRequestDto signupRequestDto = new SignupRequestDto();
            signupRequestDto.setEmail("test@google.com");
            signupRequestDto.setPassword("123213");
            signupRequestDto.setNickname("구우욷");
            signupRequestDto.setPhone("010-2134-4321");

            memberService.signup(signupRequestDto);

            LoginRequestDto loginRequestDto = new LoginRequestDto();
            loginRequestDto.setEmail("test@google.com");
            loginRequestDto.setPassword("123213");

            assertThatCode(() -> memberService.login(loginRequestDto))
                    .doesNotThrowAnyException();
        }

        @DisplayName("로그인할 때 비번틀리면 오류난다")
        @Test
        void login2() {
            SignupRequestDto signupRequestDto = new SignupRequestDto();
            signupRequestDto.setEmail("test@google.com");
            signupRequestDto.setPassword("123213");
            signupRequestDto.setNickname("구우욷");
            signupRequestDto.setPhone("010-2134-4321");

            memberService.signup(signupRequestDto);

            LoginRequestDto loginRequestDto = new LoginRequestDto();
            loginRequestDto.setEmail("test@google.com");
            loginRequestDto.setPassword("123212");

            assertThatThrownBy(() -> memberService.login(loginRequestDto))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }
}