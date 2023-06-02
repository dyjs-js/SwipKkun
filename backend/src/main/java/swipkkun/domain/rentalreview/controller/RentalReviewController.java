package swipkkun.domain.rentalreview.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import swipkkun.domain.rentalreview.dto.ReviewCreateRequestDto;
import swipkkun.domain.rentalreview.entity.RentalReview;
import swipkkun.domain.rentalreview.service.RentalReviewService;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/rental-review")
@RestController
@Slf4j
public class RentalReviewController {
    private final RentalReviewService rentalReviewService;

//    @GetMapping("/reviews/{id}")
//    public ResponseEntity<String> getReviews(@PathVariable("id") int id) {
//        List<RentalReview> reviews = rentalReviewService.getMemberReviews(id);
//        for (RentalReview review : reviews) {
//            log.info("내용 : {}", review.getRentalReviewContent());
//        }
//        return ResponseEntity.ok().body("reviews");
//    }

    @PostMapping("create-review")
    public String createReview(@RequestBody ReviewCreateRequestDto reviewCreateRequestDto) {
        rentalReviewService.createReview(reviewCreateRequestDto);
        return "success";
    }
}