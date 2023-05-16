package swipkkun.domain.member.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MemberException extends RuntimeException{
    private ErrorCode errorCode;
    private String message;
}
