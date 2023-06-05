package swipkkun.domain.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import swipkkun.domain.member.dto.*;
import swipkkun.domain.member.entity.Member;
import swipkkun.domain.member.entity.Role;
import swipkkun.domain.member.exception.MemberErrorCode;
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
    private final Long ACCESS_TOKEN_EXPIRED_MS = 1000 * 60 * 30L; // 1000ms * 60 * 10 = 30분
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

    public String checkEmailDuplicate(EmailDuplicateRequestDto requestDto) {
        String email = requestDto.getEmail();
        Optional<Member> member = findByEmail(email);
        if (member.isPresent()) {
            return "해당 이메일은 사용하실 수 없습니다";
        }
        return "사용 가능한 이메일입니다";
    }

    public String checkNicknameDuplicate(NicknameDuplicateRequestDto requestDto) {
        String nickname = requestDto.getNickname();
        Optional<Member> member = findByNickname(nickname);
        if (member.isPresent()) {
            return "해당 닉네임은 사용하실 수 없습니다";
        }
        return "사용 가능한 닉네임입니다";
    }

    private boolean validateNicknameFormat(String nickname) {
        if (nickname.length() >= 10 || containsSpecialCharacter(nickname)) {
            return false;
        }

        return true;
    }

    private static boolean containsSpecialCharacter(String nickname) {
        String specialCharacters = "!@#$%^&*()_+{}|:\"<>?-=[];',./~`";

        for (int i = 0; i < nickname.length(); i++) {
            char ch = nickname.charAt(i);
            if (specialCharacters.contains(String.valueOf(ch))) {
                return true;
            }
        }
        return false;
    }

    private void validateSignupRequest(SignupRequestDto requestDto) {
        String email = requestDto.getEmail();
        String nickname = requestDto.getNickname();
        String password = requestDto.getPassword();

        if (!validatePassword(password)) {
            throw new MemberException(MemberErrorCode.PASSWORD_FORMAT_NOT_OBSERVED, "비밀번호는 10글자 이상이고 영문/숫자가 하나씩은 포함되어야 합니다");
        }

        findByEmail(email)
                .ifPresent(member -> {
                    throw new MemberException(MemberErrorCode.USER_EMAIL_DUPLICATED, "이미 사용중인 이메일입니다");
                });

        if (!validateNicknameFormat(nickname)) {
            throw new MemberException(MemberErrorCode.NICKNAME_FORMAT_NOT_OBSERVED, "닉네임은 10글자 미만이고 알파벳&숫자만 입력해야 합니다");
        }

        findByNickname(nickname)
                .ifPresent(member -> {
                    throw new MemberException(MemberErrorCode.USER_NICKNAME_DUPLICATED, "이미 사용중인 닉네임입니다");
                });
    }

    private boolean validatePassword(String password) {
        if (password.length() >= 10) { // 문자열이 10글자 이상인지 확인
            boolean hasLetter = false;
            boolean hasDigit = false;

            for (char ch : password.toCharArray()) {
                if (Character.isLetter(ch)) {
                    hasLetter = true;
                    continue;
                }
                if (Character.isDigit(ch)) {
                    hasDigit = true;
                }
            }

            if (hasLetter && hasDigit) {
                return true; // 영문과 숫자가 모두 들어가 있는 경우
            }
        }

        return false; // 조건을 만족하지 않는 경우
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
            throw new MemberException(MemberErrorCode.EMAIL_NOT_FOUND, "이메일이 잘못됐습니다");
        }

        if (!passwordEncoder.matches(requestDto.getPassword(), member.get().getPassword())) {
            throw new MemberException(MemberErrorCode.WRONG_PASSWORD, "비밀번호가 잘못됐습니다");
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

    public MemberInfoDto getMemberInfo(int id, String accessToken) {
        String email = tokenProvider.getEmailFromToken(accessToken, jwtKey);
        Optional<Member> memberByEmail = findByEmail(email);

        if (memberByEmail.isEmpty() || memberByEmail.get().getMemberId() != id) {
            throw new MemberException(MemberErrorCode.ID_NOT_CORRESPOND, "접근 권한이 없습니다");
        }

        MemberInfoDto memberInfo = new MemberInfoDto();
        Optional<Member> member = memberRepository.findByMemberId(id);

        if (member.isEmpty()) {
            throw new MemberException(MemberErrorCode.ID_NOT_FOUND, "해당 유저를 찾을 수 없습니다");
        }

        memberInfo.setMemberId(member.get().getMemberId());
        memberInfo.setEmail(member.get().getEmail());
        memberInfo.setNickname(member.get().getNickname());
        memberInfo.setPhone(member.get().getPhone());

        return memberInfo;
    }

    public Optional<Member> findByEmail(String email) {
        return memberRepository.findByEmail(email);
    }

    public Optional<Member> findByNickname(String nickname) {
        return memberRepository.findByNickname(nickname);
    }

}
