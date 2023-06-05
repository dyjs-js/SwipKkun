package swipkkun.domain.RentalPost.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import swipkkun.domain.RentalPost.entity.RentalPost;
import java.util.Optional;

public interface RentalPostRepository extends JpaRepository<RentalPost, Integer> {
    Optional<RentalPost> findRentalPostByProductIdx(int productIdx);
}
