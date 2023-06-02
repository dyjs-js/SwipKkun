package swipkkun.domain.rentalreview.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import swipkkun.domain.rentalreview.entity.RentalReview;

import java.util.Optional;

public interface RentalReviewRepository extends JpaRepository<RentalReview, Integer> {
    RentalReview save(RentalReview rentalReview);
    Optional<RentalReview> findByRentalReviewId(int id);
}
