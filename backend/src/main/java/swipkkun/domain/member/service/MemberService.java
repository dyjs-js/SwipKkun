package swipkkun.domain.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import swipkkun.domain.member.entity.Member;
import swipkkun.domain.member.dto.LoginRequestDto;
import swipkkun.domain.member.dto.SignupRequestDto;
import swipkkun.domain.member.repository.MemberRepository;

import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public void signup(SignupRequestDto requestDto) {
        validateDuplicate(requestDto);

        String encodedPassword = passwordEncoder.encode(requestDto.getPassword());
        Member member = new Member();
        member.setEmail(requestDto.getEmail());
        member.setPassword(encodedPassword);
        member.setNickname(requestDto.getNickname());
        member.setPhone(requestDto.getPhone());

        memberRepository.save(member);
    }

    private void validateDuplicate(SignupRequestDto requestDto) {
        String email = requestDto.getEmail();
        String nickname = requestDto.getNickname();

        memberRepository.findByEmail(email)
                .ifPresent(member -> {
                    throw new IllegalArgumentException("이메일이 중복됩니다");
                });
        memberRepository.findByNickname(nickname)
                .ifPresent(member -> {
                    throw new IllegalArgumentException("닉네임이 중복됩니다");
                });
    }

    public void login(LoginRequestDto requestDto) {
        Member member = findByEmail(requestDto.getEmail());
        if (!passwordEncoder.matches(requestDto.getPassword(), member.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 잘못됐습니다!!");
        }
    }

    public Optional<Member> findByEmail(String email) {
        return memberRepository.findByEmail(email);
    }

    public Optional<Member> findByNickname(String nickname) {
        return memberRepository.findByNickname(nickname);
    }

}
