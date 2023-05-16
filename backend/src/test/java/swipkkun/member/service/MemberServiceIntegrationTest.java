package swipkkun.member.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import swipkkun.domain.member.entity.Member;
import swipkkun.domain.member.dto.LoginRequestDto;
import swipkkun.domain.member.dto.SignupRequestDto;
import swipkkun.domain.member.exception.MemberException;
import swipkkun.domain.member.repository.MemberRepository;
import swipkkun.domain.member.service.MemberService;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {
    @Autowired
    MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Nested
    class SignupTest {
        @DisplayName("회원가입 테스트")
        @Test
        void signup1() {
            SignupRequestDto requestDto = new SignupRequestDto();
            requestDto.setEmail("test1@google.com");
            requestDto.setPassword("123213");
            requestDto.setNickname("구우욷");
            requestDto.setPhone("010-2134-4321");

            memberService.signup(requestDto);
            Member joinedMember = memberService.findByEmail("test1@google.com").get();

            assertThat(joinedMember.getEmail()).isEqualTo(requestDto.getEmail());
        }

        @DisplayName("회원가입 테스트 - 중복된 이메일이면 오류")
        @Test
        void signup2() {
            SignupRequestDto requestDto = new SignupRequestDto();
            requestDto.setEmail("test1@google.com");
            requestDto.setPassword("123213");
            requestDto.setNickname("구우욷");
            requestDto.setPhone("010-2134-4321");

            memberService.signup(requestDto);
            requestDto.setNickname("너무나도 좋아용");

            assertThatThrownBy(() -> memberService.signup(requestDto))
                    .isInstanceOf(MemberException.class)
                    .hasMessageContaining("이미 사용중인 이메일입니다");

        }

        @DisplayName("회원가입 테스트 - 중복된 닉네임이면 오류")
        @Test
        void signup3() {
            SignupRequestDto requestDto = new SignupRequestDto();
            requestDto.setEmail("test1@google.com");
            requestDto.setPassword("123213");
            requestDto.setNickname("구우욷");
            requestDto.setPhone("010-2134-4321");

            memberService.signup(requestDto);
            requestDto.setEmail("test2@naver.com");

            assertThatThrownBy(() -> memberService.signup(requestDto))
                    .isInstanceOf(MemberException.class)
                    .hasMessageContaining("이미 사용중인 닉네임입니다");

        }
    }

    @Nested
    class LoginTest {
        @DisplayName("로그인 테스트")
        @Test
        void login() {
            SignupRequestDto signupRequestDto = new SignupRequestDto();
            signupRequestDto.setEmail("test1@google.com");
            signupRequestDto.setPassword("123213");
            signupRequestDto.setNickname("구우욷");
            signupRequestDto.setPhone("010-2134-4321");

            memberService.signup(signupRequestDto);

            LoginRequestDto loginRequestDto = new LoginRequestDto();
            loginRequestDto.setEmail("test1@google.com");
            loginRequestDto.setPassword("123213");

            assertThatCode(() -> memberService.login(loginRequestDto))
                    .doesNotThrowAnyException();
        }

        @DisplayName("로그인할 때 비번틀리면 오류난다")
        @Test
        void login2() {
            SignupRequestDto signupRequestDto = new SignupRequestDto();
            signupRequestDto.setEmail("test1@google.com");
            signupRequestDto.setPassword("123213");
            signupRequestDto.setNickname("구우욷");
            signupRequestDto.setPhone("010-2134-4321");

            memberService.signup(signupRequestDto);

            LoginRequestDto loginRequestDto = new LoginRequestDto();
            loginRequestDto.setEmail("test1@google.com");
            loginRequestDto.setPassword("123212");

            assertThatThrownBy(() -> memberService.login(loginRequestDto))
                    .isInstanceOf(MemberException.class)
                    .hasMessageContaining("비밀번호가 잘못됐습니다");
        }

        @DisplayName("로그인할 때 이메일틀리면 오류난다")
        @Test
        void login3() {
            SignupRequestDto signupRequestDto = new SignupRequestDto();
            signupRequestDto.setEmail("test1@google.com");
            signupRequestDto.setPassword("123213");
            signupRequestDto.setNickname("구우욷");
            signupRequestDto.setPhone("010-2134-4321");

            memberService.signup(signupRequestDto);

            LoginRequestDto loginRequestDto = new LoginRequestDto();
            loginRequestDto.setEmail("test12@google.com");
            loginRequestDto.setPassword("123213");

            assertThatThrownBy(() -> memberService.login(loginRequestDto))
                    .isInstanceOf(MemberException.class)
                    .hasMessageContaining("이메일이 잘못됐습니다");
        }
    }
}