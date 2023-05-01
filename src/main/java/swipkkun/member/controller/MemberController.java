package swipkkun.member.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import swipkkun.member.dto.LoginRequestDto;
import swipkkun.member.dto.SignupRequestDto;
import swipkkun.member.service.MemberService;

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
