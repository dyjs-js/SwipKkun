package swipkkun.global.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import swipkkun.global.jwt.JwtExceptionFilter;
import swipkkun.global.jwt.JwtFilter;
import swipkkun.global.jwt.JwtTokenProvider;

import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtTokenProvider tokenProvider;
    private final JwtExceptionFilter jwtExceptionFilter;
    @Value("${jwt.secret}")
    private String jwtKey;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.csrf().disable() // CSRF 공격에 대한 방어를 해제
                .cors().configurationSource(request -> {
                    var cors = new CorsConfiguration();
                    cors.setAllowedOrigins(List.of("http://localhost:3000"));
                    cors.setAllowedMethods(List.of("GET","POST", "PUT", "DELETE", "OPTIONS"));
                    cors.setAllowedHeaders(List.of("*"));
                    return cors;
                })
                .and() // cors 허용
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 서버를 stateless하게 유지 즉 세션 X
                .and()
                .formLogin().disable() // 시큐리티가 기본 제공하는 로그인 화면 없앰. JWT는 로그인과정을 수동으로 클래스로 만들어야 하니까
                .httpBasic().disable() // 토큰 방식을 이용할 것이므로 보안에 취약한 HttpBasic은 꺼두기 httpBasic : 필요할 때 단순히 username, password만으로 인증하는 방식
                .authorizeRequests() // HttpServletRequest를 사용하는 요청들에 대한 접근제한을 설정하겠다
//                .requestMatchers("/signup", "/").permitAll() // 요 세 놈에 대한 요청은 인증없이 접근 허용
//                .requestMatchers("/login").permitAll()
//                .anyRequest().authenticated() // 나머지에 대해선 인증을 받아야 한다.
                .requestMatchers("/api/auth/member/{id}", "/api/auth/refresh").authenticated()
                .requestMatchers("/api/auth/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(new JwtFilter(tokenProvider, jwtKey), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(jwtExceptionFilter, JwtFilter.class)
                .build();
    }
}
