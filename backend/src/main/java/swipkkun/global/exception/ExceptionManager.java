package swipkkun.global.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionManager {
    @ExceptionHandler(IllegalArgumentException.class) // IllegalArgumentException를 받아 처리할 수 있다
    public ResponseEntity<?> handleIllegalArgumentException(IllegalArgumentException e) { // ? : body에 뭐든지 들어갈 수 있다
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(e.getMessage());
    }
}
