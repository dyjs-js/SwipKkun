package swipkkun.domain.rentalreview.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import swipkkun.domain.RentalPost.entity.RentalPost;
import swipkkun.domain.member.entity.Member;
import swipkkun.domain.rentalreview.dto.ReviewResponseDto;
import swipkkun.domain.rentalreview.dto.ReviewsResponseDto;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
public class RentalReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rentalReviewId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_idx", nullable = false)
    private RentalPost rentalPost;

    @Column(name = "rental_review_content", nullable = false)
    private String rentalReviewContent;

    @Column(name = "rental_review_score", nullable = false)
    private int rentalReviewScore;

    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime updatedDate;

    public ReviewResponseDto toDto() {
        return ReviewResponseDto.builder()
                .rentalReviewId(rentalReviewId)
                .memberId(member.getMemberId())
                .writerNickname(member.getNickname())
                .productIdx(rentalPost.getProductIdx())
                .rentalReviewContent(rentalReviewContent)
                .rentalReviewScore(rentalReviewScore)
                .updatedDate(updatedDate)
                .build();
    }
}
