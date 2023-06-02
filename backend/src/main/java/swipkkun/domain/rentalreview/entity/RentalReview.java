package swipkkun.domain.rentalreview.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import swipkkun.domain.member.entity.Member;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
public class RentalReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rentalReviewId;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Column(name = "rental_post_id", nullable = false)
    private int rentalPostId;

    @Column(name = "rental_review_content", nullable = false)
    private String rentalReviewContent;

    @Column(name = "rental_review_score", nullable = false)
    private int rentalReviewScore;

    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime updatedDate;
}
