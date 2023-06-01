package swipkkun.domain.member.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class LoginResponseDto {
    private TokenDTO token;
    @JsonProperty("member_info")
    private MemberInfoDto memberInfo;
}
