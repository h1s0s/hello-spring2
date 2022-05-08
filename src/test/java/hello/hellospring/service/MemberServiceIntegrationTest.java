package hello.hellospring.service;

import hello.hellospring.domain.MemberVo;
import hello.hellospring.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class MemberServiceIntegrationTest {

    @Autowired MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Test
    void 회원가입() {
        //given
        MemberVo memberVo = new MemberVo();
        memberVo.setName("spring");

        //when
        Long saveId = memberService.join(memberVo);

        //then
        MemberVo findMember = memberService.findOne(saveId).get();
        Assertions.assertThat(memberVo.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외(){
        //given
        MemberVo memberVo1 = new MemberVo();
        memberVo1.setName("spring");

        MemberVo memberVo2 = new MemberVo();
        memberVo2.setName("spring");

        //when

        //then
    }
}
