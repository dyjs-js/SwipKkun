package swipkkun.domain.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import swipkkun.domain.member.entity.Member;
import swipkkun.domain.member.dto.LoginRequestDto;
import swipkkun.domain.member.dto.SignupRequestDto;
import swipkkun.domain.member.exception.ErrorCode;
import swipkkun.domain.member.exception.MemberException;
import swipkkun.domain.member.repository.MemberRepository;
import swipkkun.global.jwt.JwtTokenProvider;

import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider tokenProvider;
    @Value("${jwt.secret}")
    private String jwtKey;
    private Long expiredMs = 1000 * 60 * 60L; // 1000ms * 60 * 60 = 1시간

    public void signup(SignupRequestDto requestDto) {
        validateSignupRequest(requestDto);

        String encodedPassword = passwordEncoder.encode(requestDto.getPassword());
        Member member = new Member();
        member.setEmail(requestDto.getEmail());
        member.setPassword(encodedPassword);
        member.setNickname(requestDto.getNickname());
        member.setPhone(requestDto.getPhone());

        memberRepository.save(member);
    }

    private void validateSignupRequest(SignupRequestDto requestDto) {
        String email = requestDto.getEmail();
        String nickname = requestDto.getNickname();

        findByEmail(email)
                .ifPresent(member -> {
                    throw new MemberException(ErrorCode.USER_EMAIL_DUPLICATED, "이미 사용중인 이메일입니다");
                });
        findByNickname(nickname)
                .ifPresent(member -> {
                    throw new MemberException(ErrorCode.USER_NICKNAME_DUPLICATED, "이미 사용중인 닉네임입니다");
                });
    }

    public String login(LoginRequestDto requestDto) {
        validateLoginRequest(requestDto);
        String accessToken = tokenProvider.createToken(requestDto.getEmail(), jwtKey, expiredMs);
        return accessToken;
    }

    private void validateLoginRequest(LoginRequestDto requestDto) {
        Optional<Member> member = findByEmail(requestDto.getEmail());
        if (member.isEmpty()) {
            throw new MemberException(ErrorCode.EMAIL_NOT_FOUND, "이메일이 잘못됐습니다");
        }

        if (!passwordEncoder.matches(requestDto.getPassword(), member.get().getPassword())) {
            throw new MemberException(ErrorCode.WRONG_PASSWORD, "비밀번호가 잘못됐습니다");
        }
    }

    public Optional<Member> findByEmail(String email) {
        return memberRepository.findByEmail(email);
    }

    public Optional<Member> findByNickname(String nickname) {
        return memberRepository.findByNickname(nickname);
    }

}
