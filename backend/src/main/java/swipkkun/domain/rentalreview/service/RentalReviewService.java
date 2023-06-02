package swipkkun.domain.rentalreview.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import swipkkun.domain.member.entity.Member;
import swipkkun.domain.member.exception.MemberErrorCode;
import swipkkun.domain.member.exception.MemberException;
import swipkkun.domain.member.repository.MemberRepository;
import swipkkun.domain.rentalreview.dto.ReviewCreateRequestDto;
import swipkkun.domain.rentalreview.entity.RentalReview;
import swipkkun.domain.rentalreview.exception.ReviewErrorCode;
import swipkkun.domain.rentalreview.exception.ReviewException;
import swipkkun.domain.rentalreview.repository.RentalReviewRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class RentalReviewService {
    private final MemberRepository memberRepository;
    private final RentalReviewRepository rentalReviewRepository;


    public List<RentalReview> getMemberReviews(int member_id) {
        Member member = memberRepository.findByMemberId(member_id).
                orElseThrow(() -> new MemberException(MemberErrorCode.ID_NOT_FOUND, "일치하는 유저가 없습니다"));

        return member.getRentalReviews();
    }

    public void createReview(ReviewCreateRequestDto reviewCreateRequestDto) {
        validateReviewCreateRequest(reviewCreateRequestDto);

        Member member = memberRepository.findByMemberId(reviewCreateRequestDto.getMemberId())
                .orElseThrow(() -> new MemberException(MemberErrorCode.ID_NOT_FOUND, "일치하는 유저가 없습니다"));

        RentalReview rentalReview = new RentalReview();
        rentalReview.setMember(member);
        rentalReview.setRentalPostId(reviewCreateRequestDto.getRentalPostId());
        rentalReview.setRentalReviewContent(reviewCreateRequestDto.getRentalReviewContent());
        rentalReview.setRentalReviewScore(reviewCreateRequestDto.getRentalReviewScore());

        rentalReviewRepository.save(rentalReview);
    }

    private void validateReviewCreateRequest(ReviewCreateRequestDto reviewCreateRequestDto) {
        int memberId = reviewCreateRequestDto.getMemberId();
        int rentalPostId = reviewCreateRequestDto.getRentalPostId();
        String rentalReviewContent = reviewCreateRequestDto.getRentalReviewContent();
        int rentalReviewScore = reviewCreateRequestDto.getRentalReviewScore();

        checkMemberExist(memberId);
        checkRentalPostExist(rentalPostId);
        checkReviewContent(rentalReviewContent);
        checkReviewScore(rentalReviewScore);
    }

    private void checkMemberExist(int memberId) {
        ;
    }

    private void checkRentalPostExist(int postId) {
        ;
    }

    private void checkReviewContent(String content) {
        if (content == null || content.isEmpty()) {
            throw new ReviewException(ReviewErrorCode.CONTENT_IS_EMPTY, "리뷰 내용을 작성해주세요");
        }
    }

    private void checkReviewScore(int score) {
        if (score < 1 || score > 5) {
            throw new ReviewException(ReviewErrorCode.INVALID_REVIEW_SCORE_RANGE, "별점은 1 ~ 5까지만 가능합니다");
        }
    }
}
