package swipkkun.domain.rentalreview.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public class ReviewException extends RuntimeException {
    private ReviewErrorCode reviewErrorCode;
    private String message;
}
