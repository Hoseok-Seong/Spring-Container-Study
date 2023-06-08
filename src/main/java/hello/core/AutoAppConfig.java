package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

// basePackages 지정하지 않을 시, 설정 정보 클래스의 패키지가 시작 위치가 된다
@Configuration
@ComponentScan(
        basePackages = "hello.core",
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION,
        classes = Configuration.class))
public class AutoAppConfig {

    // 수동 빈 등록이 자동 빈 등록보다 우선권을 가진다
    // application.properties에 아래를 true로 해야 에러가 나지 않는다(기본은 false)
    // spring.main.allow-bean-definition-overriding=true
    @Bean(name = "memoryMemberRepository")
//    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
