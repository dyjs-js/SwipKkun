package swipkkun.domain.member.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberInfoDto {
    @JsonProperty("member_id")
    private int memberId;
    private String email;
    private String nickname;
    private String phone;
}
