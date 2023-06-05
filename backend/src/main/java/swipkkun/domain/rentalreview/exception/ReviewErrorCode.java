package swipkkun.domain.rentalreview.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ReviewErrorCode {
    CONTENT_IS_EMPTY(HttpStatus.BAD_REQUEST, ""),
    INVALID_REVIEW_SCORE_RANGE(HttpStatus.BAD_REQUEST, "");

    private HttpStatus httpStatus;
    private String message;
}
