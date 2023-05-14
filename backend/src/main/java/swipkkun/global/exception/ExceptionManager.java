package swipkkun.global.exception;

import io.jsonwebtoken.SignatureException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import swipkkun.domain.member.exception.MemberException;

@ControllerAdvice
public class ExceptionManager {
    @ExceptionHandler(MemberException.class) // MemberException을 받아 처리할 수 있다
    public ResponseEntity<?> handleMemberException(MemberException e) { // ? : body에 뭐든지 들어갈 수 있다
        return ResponseEntity.status(e.getErrorCode().getHttpStatus())
                .body(e.getErrorCode().name() + " " +  e.getMessage());
    }
}
