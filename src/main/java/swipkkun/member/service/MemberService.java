package swipkkun.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import swipkkun.member.domain.Member;
import swipkkun.member.dto.LoginRequestDto;
import swipkkun.member.dto.SignupRequestDto;
import swipkkun.member.repository.MemberRepository;

import java.util.Optional;

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
