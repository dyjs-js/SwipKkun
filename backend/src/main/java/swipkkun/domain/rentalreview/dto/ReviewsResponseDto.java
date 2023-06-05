package swipkkun.domain.rentalreview.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class ReviewsResponseDto {
    @JsonProperty("average_review_score")
    private double averageReviewScore;
    private List<ReviewResponseDto> reviews;
}
