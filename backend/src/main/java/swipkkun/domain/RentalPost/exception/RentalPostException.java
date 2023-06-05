package swipkkun.domain.RentalPost.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RentalPostException  extends RuntimeException {
    private RentalPostErrorCode rentalPostErrorCode;
    private String message;
}
