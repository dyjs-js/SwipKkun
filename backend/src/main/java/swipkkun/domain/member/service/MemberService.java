package swipkkun.domain.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import swipkkun.domain.member.entity.Member;
import swipkkun.domain.member.dto.LoginRequestDto;
import swipkkun.domain.member.dto.SignupRequestDto;
import swipkkun.domain.member.repository.MemberRepository;

@RequiredArgsConstructor
@Service
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public void signup(SignupRequestDto requestDto) {
        String encodedPassword = passwordEncoder.encode(requestDto.getPassword());
        Member member = new Member();
        member.setEmail(requestDto.getEmail());
        member.setPassword(encodedPassword);
        member.setNickname(requestDto.getNickname());
        member.setPhone(requestDto.getPhone());
        memberRepository.save(member);
    }

    public void login(LoginRequestDto requestDto) {
        Member member = findByEmail(requestDto.getEmail());
        if (!passwordEncoder.matches(requestDto.getPassword(), member.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 잘못됐습니다!!");
        }
    }

    public Member findByEmail(String email) {
        return memberRepository.findByEmail(email).get();
    }

}
