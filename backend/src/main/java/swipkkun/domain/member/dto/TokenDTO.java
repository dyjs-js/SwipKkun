package swipkkun.domain.member.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenDTO {
    @JsonProperty("access_token")
    private String accessToken;
    @JsonIgnore // 해당 필드는 안 보이게 된다
    private String refreshToken;
}
