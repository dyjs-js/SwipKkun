package swipkkun.domain.member.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import swipkkun.domain.member.dto.SignupRequestDto;
import swipkkun.domain.member.service.MemberService;
import swipkkun.domain.member.dto.LoginRequestDto;

@RequiredArgsConstructor
@RestController
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/signup")
    public String signup(@RequestBody SignupRequestDto requestDto) {
        memberService.signup(requestDto);
        return "success";
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequestDto requestDto) {
        memberService.login(requestDto);
        return "success";
    }
}
