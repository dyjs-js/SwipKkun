package swipkkun.domain.RentalPost.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import swipkkun.domain.member.entity.Member;
import swipkkun.domain.rentalreview.entity.RentalReview;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
public class RentalPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productIdx;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Column(name = "product_name", nullable = false)
    private String productName;

    @Column(name = "product_content", nullable = false)
    private String productContent;

    @Column(name = "precaution", nullable = false)
    private String precaution;

    @Column(name = "product_price", nullable = false)
    private int productPrice;

    @Column(name = "hit_cnt", nullable = false)
    private int hitCnt;

    @Column(name = "product_img", nullable = false)
    private String productImg;

    @Column(name = "product_address", nullable = false)
    private String productAddress;

    @Column(name = "product_hash_tag", nullable = false)
    private String productHashTag;

    @Column(name = "product_status", nullable = false)
    private int productStatus;

    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime updatedDate;

    @OneToMany(mappedBy = "rentalPost", cascade = CascadeType.ALL)
    private List<RentalReview> rentalReviews;

}
