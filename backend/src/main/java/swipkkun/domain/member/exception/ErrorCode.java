package swipkkun.domain.member.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    USER_EMAIL_DUPLICATED(HttpStatus.CONFLICT, ""),
    USER_NICKNAME_DUPLICATED(HttpStatus.CONFLICT, ""),
    EMAIL_NOT_FOUND(HttpStatus.NOT_FOUND, ""),
    WRONG_PASSWORD(HttpStatus.UNAUTHORIZED, "");

    private HttpStatus httpStatus;
    private String message;
}
