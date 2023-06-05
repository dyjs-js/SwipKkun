package swipkkun.domain.RentalPost.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import swipkkun.domain.RentalPost.entity.RentalPost;
import swipkkun.domain.RentalPost.exception.RentalPostErrorCode;
import swipkkun.domain.RentalPost.exception.RentalPostException;
import swipkkun.domain.RentalPost.repository.RentalPostRepository;

@RequiredArgsConstructor
@Service
@Transactional
public class RentalPostService {
    private final RentalPostRepository rentalPostRepository;

    public RentalPost getRentalReview(int productIdx) {
        RentalPost rentalPost = rentalPostRepository.findRentalPostByProductIdx(productIdx)
                .orElseThrow(() -> new RentalPostException(RentalPostErrorCode.RENTAL_POST_NOT_FOUND
                        , "일치하는 대여글이 없습니다"));

        return rentalPost;
    }
}
