package swipkkun.domain.rentalreview.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class ReviewResponseDto {
    @JsonProperty("rental_review_id")
    private int rentalReviewId;

    @JsonProperty("member_id")
    private int memberId;

    @JsonProperty("writer_nickname")
    private String writerNickname;

    @JsonProperty("product_idx")
    private int productIdx;

    @JsonProperty("rental_review_content")
    private String rentalReviewContent;

    @JsonProperty("rental_review_score")
    private int rentalReviewScore;

    @JsonProperty("updated_date")
    private LocalDateTime updatedDate;
}
