package swipkkun.domain.member.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    USER_EMAIL_DUPLICATED(HttpStatus.CONFLICT, ""),
    USER_NICKNAME_DUPLICATED(HttpStatus.CONFLICT, ""),
    PASSWORD_FORMAT_NOT_OBSERVED(HttpStatus.BAD_REQUEST, ""),
    NICKNAME_FORMAT_NOT_OBSERVED(HttpStatus.BAD_REQUEST, ""),
    EMAIL_NOT_FOUND(HttpStatus.NOT_FOUND, ""),
    WRONG_PASSWORD(HttpStatus.UNAUTHORIZED, ""),
    TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED, ""),
    ID_NOT_FOUND(HttpStatus.NOT_FOUND, ""),
    ID_NOT_CORRESPOND(HttpStatus.UNAUTHORIZED, "");

    private HttpStatus httpStatus;
    private String message;
}
