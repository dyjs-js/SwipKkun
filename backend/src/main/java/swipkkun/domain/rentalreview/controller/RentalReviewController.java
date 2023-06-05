package swipkkun.domain.rentalreview.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import swipkkun.domain.RentalPost.entity.RentalPost;
import swipkkun.domain.RentalPost.service.RentalPostService;
import swipkkun.domain.rentalreview.dto.ReviewCreateRequestDto;
import swipkkun.domain.rentalreview.dto.ReviewResponseDto;
import swipkkun.domain.rentalreview.dto.ReviewsResponseDto;
import swipkkun.domain.rentalreview.entity.RentalReview;
import swipkkun.domain.rentalreview.service.RentalReviewService;

import java.util.*;

@RequiredArgsConstructor
@RequestMapping("/api/rental-review")
@RestController
@Slf4j
public class RentalReviewController {
    private final RentalReviewService rentalReviewService;
    private final RentalPostService rentalPostService;

    @GetMapping("/reviews/{product_idx}")
    public ResponseEntity<ReviewsResponseDto> getReviews(@PathVariable("product_idx") int productIdx) {
        RentalPost rentalPost = rentalPostService.getRentalReview(productIdx);
        List<RentalReview> reviews = rentalPost.getRentalReviews();

        List<ReviewResponseDto> reviewDtos = new ArrayList<>();
        double reviewScoreSum = 0;
        for (RentalReview review : reviews) {
            reviewDtos.add(review.toDto());
            reviewScoreSum += review.getRentalReviewScore();
        }

        ReviewsResponseDto reviewsResponse = ReviewsResponseDto.builder()
                .averageReviewScore(Math.round(reviewScoreSum / reviews.size() * 10.0) / 10.0)
                .reviews(reviewDtos)
                .build();

        return ResponseEntity.ok().body(reviewsResponse);
    }


    @PostMapping("/create-review")
    public String createReview(@RequestBody ReviewCreateRequestDto reviewCreateRequestDto) {
        rentalReviewService.createReview(reviewCreateRequestDto);
        return "success";
    }

    @DeleteMapping("/delete-review/{rentalReviewId}")
    public ResponseEntity<Map<String, Boolean>> deleteReview(@PathVariable("rentalReviewId") int rentalReviewId) {
        boolean deleteResult = rentalReviewService.deleteReview(rentalReviewId);
        Map<String, Boolean> result = new HashMap<>();
        result.put("delete_sucess", deleteResult);
        return ResponseEntity.ok().body(result);
    }
}
