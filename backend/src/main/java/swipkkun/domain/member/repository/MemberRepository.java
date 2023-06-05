package swipkkun.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import swipkkun.domain.member.entity.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Integer> {
    Member save(Member member);
    Optional<Member> findByMemberId(int id);
    Optional<Member> findByEmail(String email);
    Optional<Member> findByNickname(String nickname);
}
