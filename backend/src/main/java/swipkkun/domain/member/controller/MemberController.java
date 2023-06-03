package swipkkun.domain.member.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import swipkkun.domain.member.dto.*;
import swipkkun.domain.member.service.MemberService;
import swipkkun.global.util.HeaderUtil;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RequestMapping("/api/auth")
@RestController
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity<Map<String, Object>> signup(HttpServletResponse response, @RequestBody SignupRequestDto requestDto) {
        memberService.signup(requestDto);

        LoginRequestDto loginRequest = new LoginRequestDto();
        loginRequest.setEmail(requestDto.getEmail());
        loginRequest.setPassword(requestDto.getPassword());

        TokenDTO tokenResponse = memberService.login(loginRequest);
        HeaderUtil.setRefreshToken(response, tokenResponse.getRefreshToken());

        Map<String, Object> res = new HashMap<>();
        res.put("access_token", tokenResponse.getAccessToken());
        res.put("member_id", memberService.findByEmail(requestDto.getEmail()).get().getMemberId());

        return ResponseEntity.ok().body(res);
    }

    @PostMapping("/check-email")
    public ResponseEntity<String> checkEmail(@RequestBody EmailDuplicateRequestDto requestDto) {
        String checkRes = memberService.checkEmailDuplicate(requestDto);
        return ResponseEntity.ok().body(checkRes);
    }

    @PostMapping("/check-nickname")
    public ResponseEntity<String> checkNickname(@RequestBody NicknameDuplicateRequestDto requestDto) {
        String checkRes = memberService.checkNicknameDuplicate(requestDto);
        return ResponseEntity.ok().body(checkRes);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(HttpServletResponse response, @RequestBody LoginRequestDto requestDto) {
        TokenDTO tokenResponse = memberService.login(requestDto);
        // refresh token은 쿠키에 담아 보낸다
        HeaderUtil.setRefreshToken(response, tokenResponse.getRefreshToken());

        Map<String, Object> res = new HashMap<>();
        res.put("access_token", tokenResponse.getAccessToken());
        res.put("member_id", memberService.findByEmail(requestDto.getEmail()).get().getMemberId());

        return ResponseEntity.ok().body(res);
    }

    @PostMapping("/refresh")
    public ResponseEntity<TokenDTO> refresh(HttpServletRequest request) {
        String accessToken = HeaderUtil.getAccessToken(request);
        String refreshToken = HeaderUtil.getRefreshToken(request);

        TokenDTO tokenRequest = new TokenDTO();
        tokenRequest.setAccessToken(accessToken);
        tokenRequest.setRefreshToken(refreshToken);

        TokenDTO newTokenResponse = memberService.refresh(tokenRequest);

        return ResponseEntity.ok().body(newTokenResponse);
    }

    @GetMapping("/member/{id}")
    public ResponseEntity<MemberInfoDto> getMemberInfo(@PathVariable("id") int id, HttpServletRequest request) {
        String accessToken = HeaderUtil.getAccessToken(request);
        MemberInfoDto memberInfo = memberService.getMemberInfo(id, accessToken);
        return ResponseEntity.ok().body(memberInfo);
    }
}
