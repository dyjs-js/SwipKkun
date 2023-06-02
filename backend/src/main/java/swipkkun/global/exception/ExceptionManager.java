package swipkkun.global.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import swipkkun.domain.member.exception.MemberException;
import swipkkun.domain.rentalreview.exception.ReviewException;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionManager {
    @ExceptionHandler(MemberException.class) // MemberException를 받아 처리할 수 있다
    public ResponseEntity<?> handleMemberException(MemberException e) { // ? : body에 뭐든지 들어갈 수 있다
        return ResponseEntity.status(e.getMemberErrorCode().getHttpStatus())
                .body(e.getMemberErrorCode().name() + " " +  e.getMessage());
    }

    @ExceptionHandler(ReviewException.class)
    public ResponseEntity<?> handleReviewException(ReviewException e) {
        String errorMessage = e.getMessage();
        Map<String, String> errorRes = new HashMap<>();
        errorRes.put("error_message", errorMessage);

        return ResponseEntity.status(e.getReviewErrorCode().getHttpStatus())
                .body(errorRes);
    }
}
