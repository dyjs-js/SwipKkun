package swipkkun.domain.member.service;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import swipkkun.domain.member.dto.TokenDTO;
import swipkkun.domain.member.entity.Member;
import swipkkun.domain.member.dto.LoginRequestDto;
import swipkkun.domain.member.dto.SignupRequestDto;
import swipkkun.domain.member.entity.Role;
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
    private final Long ACCESS_TOKEN_EXPIRED_MS = 1000 * 60 * 10L; // 1000ms * 60 * 10 = 10분
    private final Long REFRESH_TOKEN_EXPIRED_MS = 1000 * 60 * 60 * 24 * 14L; // 1000ms * 60 * 60 * 24 * 14= 14일

    public void signup(SignupRequestDto requestDto) {
        validateSignupRequest(requestDto);

        String encodedPassword = passwordEncoder.encode(requestDto.getPassword());
        Member member = new Member();
        member.setEmail(requestDto.getEmail());
        member.setPassword(encodedPassword);
        member.setNickname(requestDto.getNickname());
        member.setPhone(requestDto.getPhone());
        member.setRole(Role.USER);

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

    public TokenDTO login(LoginRequestDto requestDto) {
        validateLoginRequest(requestDto);

        UserDetails userDetails = memberRepository.findByEmail(requestDto.getEmail()).get();
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());

        String accessToken = tokenProvider.createAccessToken(authentication, jwtKey, ACCESS_TOKEN_EXPIRED_MS);
        String refreshToken = tokenProvider.createRefreshToken(jwtKey, REFRESH_TOKEN_EXPIRED_MS);

        TokenDTO tokenDto = new TokenDTO();
        tokenDto.setAccessToken(accessToken);
        tokenDto.setRefreshToken(refreshToken);

        return tokenDto;
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

    public TokenDTO refresh(TokenDTO tokenRequest) {
        String accessToken = tokenRequest.getAccessToken();
        String refreshToken = tokenRequest.getRefreshToken();

        try {
            tokenProvider.validateToken(refreshToken, jwtKey);
        } catch (IllegalArgumentException ex) {

        }

        Authentication authentication = tokenProvider.getAuthentication(accessToken, jwtKey);
        String newAccessToken = tokenProvider.createAccessToken(authentication, jwtKey, ACCESS_TOKEN_EXPIRED_MS);

        TokenDTO tokenResponse = new TokenDTO();
        tokenResponse.setAccessToken(newAccessToken);
        tokenResponse.setRefreshToken(refreshToken);

        return tokenResponse;
    }

    public Optional<Member> findByEmail(String email) {
        return memberRepository.findByEmail(email);
    }

    public Optional<Member> findByNickname(String nickname) {
        return memberRepository.findByNickname(nickname);
    }

}
