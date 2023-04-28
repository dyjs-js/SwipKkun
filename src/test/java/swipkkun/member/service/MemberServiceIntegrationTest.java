package swipkkun.member.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import swipkkun.member.domain.Member;
import swipkkun.member.repository.MemberRepository;

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
            Member member = new Member();
            member.setEmail("test@google.com");
            member.setPassword("123213");
            member.setNickname("구우욷");
            member.setPhone("010-2134-4321");

            memberService.signup(member);
            Member joinedMember = memberService.findByEmail("test@google.com");

            assertThat(joinedMember.getEmail()).isEqualTo(member.getEmail());
        }
    }
}