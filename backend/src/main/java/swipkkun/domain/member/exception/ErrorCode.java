package swipkkun.domain.member.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    USER_EMAIL_DUPLICATED(HttpStatus.CONFLICT, ""),
    USER_NICKNAME_DUPLICATED(HttpStatus.CONFLICT, "");

    private HttpStatus httpStatus;
    private String message;
}
