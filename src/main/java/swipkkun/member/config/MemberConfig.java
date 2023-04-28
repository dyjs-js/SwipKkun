package swipkkun.member.config;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import swipkkun.member.repository.JpaMemberRepository;
import swipkkun.member.repository.MemberRepository;
import swipkkun.member.service.MemberService;

@Configuration
public class MemberConfig {
    private final EntityManager em;

    @Autowired
    public MemberConfig(EntityManager em) {
        this.em = em;
    }

    @Bean
    public MemberRepository memberRepository() {
        return new JpaMemberRepository(em);
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }
}
