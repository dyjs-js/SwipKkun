package swipkkun.member.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import swipkkun.member.domain.Member;

import java.util.Optional;

@RequiredArgsConstructor
public class JpaMemberRepository implements MemberRepository {
    private final EntityManager em;

    @Override
    public void save(Member member) {
        em.persist(member);
    }

    @Override
    public Optional<Member> findByEmail(String email) {
        Member member = em.find(Member.class, email);
        return Optional.ofNullable(member);
    }
}
