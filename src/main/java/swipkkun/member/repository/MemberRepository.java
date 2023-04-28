package swipkkun.member.repository;

import swipkkun.member.domain.Member;
import java.util.Optional;

public interface MemberRepository {
    void save(Member member);

    Optional<Member> findByEmail(String email);
}
