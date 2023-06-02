package swipkkun.domain.rentalreview.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import swipkkun.domain.member.entity.Member;
import swipkkun.domain.member.exception.ErrorCode;
import swipkkun.domain.member.exception.MemberException;
import swipkkun.domain.member.repository.MemberRepository;
import swipkkun.domain.rentalreview.dto.ReviewCreateRequestDto;
import swipkkun.domain.rentalreview.entity.RentalReview;
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
                orElseThrow(() -> new MemberException(ErrorCode.ID_NOT_FOUND, "일치하는 유저가 없습니다"));

        return member.getRentalReviews();
    }

    public void createReview(ReviewCreateRequestDto reviewCreateRequestDto) {
        // 유효성 검사 해야 함!

        Member member = memberRepository.findByMemberId(reviewCreateRequestDto.getMemberId())
                .orElseThrow(() -> new MemberException(ErrorCode.ID_NOT_FOUND, "일치하는 유저가 없습니다"));

        RentalReview rentalReview = new RentalReview();
        rentalReview.setMember(member);
        rentalReview.setRentalPostId(reviewCreateRequestDto.getRentalPostId());
        rentalReview.setRentalReviewContent(reviewCreateRequestDto.getRentalReviewContent());
        rentalReview.setRentalReviewScore(reviewCreateRequestDto.getRentalReviewScore());

        rentalReviewRepository.save(rentalReview);
    }

    private void validateReviewCreateRequest(ReviewCreateRequestDto reviewCreateRequestDto) {

    }
}
