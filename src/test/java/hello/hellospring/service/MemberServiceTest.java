package hello.hellospring.service;

import hello.hellospring.domain.MemberVo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService = new MemberService();

    @Test
    void 회원가입() {
        //given
        MemberVo memberVo = new MemberVo();
        memberVo.setName("hello");//hello라는 이름 셋

        //when
        Long saveId = memberService.join(memberVo);//회원가입 처리

        //then
        MemberVo findMember = memberService.findOne(saveId).get();
        Assertions.assertThat(memberVo.getName()).isEqualTo(findMember.getName());
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}