package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

@Controller
public class MemberController {
    //1. Controller 어노테이션을 달면, 이 컨트롤러가 스프링 컨테이너에 들어가게 되고, 스프링이 관리함.

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        //2. 생성자에 Autowired 어노테이션을 달면, 스프링 컨테이너에 있는 memverService를 가져다 연결을 시켜줌.
        //   Service가 스프링에 올라가 있지 않으면, 에러가 남
        this.memberService = memberService;
    }
}
