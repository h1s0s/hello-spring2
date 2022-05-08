package hello.hellospring;

import hello.hellospring.repository.JdbcTemplateMemberRepository;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private DataSource dataSource;

    public SpringConfig(DataSource dataSource){

    }

    //자바코드로 직접 스프링 빈 등록하기
    //이렇게 쓰면 스프링이 뜰때, 이 컨피규레이션을 읽고, 이 메소드를 인식을 하고 빈을 등록함.
    //이 방식의 장점은
    @Bean
    public MemberService memberService(MemberRepository memberRepository) {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new JdbcTemplateMemberRepository(dataSource);
    }
}
