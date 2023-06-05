package swipkkun.domain.RentalPost.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum RentalPostErrorCode {
    RENTAL_POST_NOT_FOUND(HttpStatus.NOT_FOUND, "");

    private HttpStatus httpStatus;
    private String message;
}
