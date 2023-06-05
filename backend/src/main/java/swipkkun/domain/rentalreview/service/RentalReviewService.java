package swipkkun.domain.rentalreview.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import swipkkun.domain.RentalPost.entity.RentalPost;
import swipkkun.domain.RentalPost.exception.RentalPostErrorCode;
import swipkkun.domain.RentalPost.exception.RentalPostException;
import swipkkun.domain.RentalPost.repository.RentalPostRepository;
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
    private final RentalPostRepository rentalPostRepository;
    private final RentalReviewRepository rentalReviewRepository;

    public List<RentalReview> getMemberReviews(int member_id) {
        Member member = memberRepository.findByMemberId(member_id).
                orElseThrow(() -> new MemberException(MemberErrorCode.ID_NOT_FOUND, "일치하는 유저가 없습니다"));

        return member.getRentalReviews();
    }

    public int createReview(ReviewCreateRequestDto reviewCreateRequestDto) {
        validateReviewCreateRequest(reviewCreateRequestDto);

        Member member = memberRepository
                .findByMemberId(reviewCreateRequestDto.getMemberId()).get();
        RentalPost rentalPost = rentalPostRepository
                .findRentalPostByProductIdx(reviewCreateRequestDto.getProductIdx()).get();

        RentalReview rentalReview = new RentalReview();
        rentalReview.setMember(member);
        rentalReview.setRentalPost(rentalPost);
        rentalReview.setRentalReviewContent(reviewCreateRequestDto.getRentalReviewContent());
        rentalReview.setRentalReviewScore(reviewCreateRequestDto.getRentalReviewScore());

        rentalReviewRepository.save(rentalReview);

        return rentalReview.getRentalReviewId();
    }

    private void validateReviewCreateRequest(ReviewCreateRequestDto reviewCreateRequestDto) {
        int memberId = reviewCreateRequestDto.getMemberId();
        int productIdx = reviewCreateRequestDto.getProductIdx();
        String rentalReviewContent = reviewCreateRequestDto.getRentalReviewContent();
        int rentalReviewScore = reviewCreateRequestDto.getRentalReviewScore();

        checkMemberExist(memberId);
        checkRentalPostExist(productIdx);
        checkReviewContent(rentalReviewContent);
        checkReviewScore(rentalReviewScore);
    }

    private void checkMemberExist(int memberId) {
        memberRepository.findByMemberId(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.ID_NOT_FOUND,
                        "일치하는 유저가 없습니다"));
    }

    private void checkRentalPostExist(int productIdx) {
        rentalPostRepository.findRentalPostByProductIdx(productIdx)
                .orElseThrow(() -> new RentalPostException(RentalPostErrorCode.RENTAL_POST_NOT_FOUND,
                        "일치하는 대여글이 없습니다"));
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

    public boolean deleteReview(int rentalReviewId) {
        // 멤버 권한 체크, 게시글 존재 체크 해야 함
        rentalReviewRepository.deleteRentalReviewByRentalReviewId(rentalReviewId);
        return true;
    }
}
