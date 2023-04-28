package swipkkun.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import swipkkun.member.domain.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Integer> {
    Member save(Member member);

    Optional<Member> findByEmail(String email);
}
