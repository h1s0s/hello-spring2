package hello.hellospring.controller;

import hello.hellospring.domain.MemberVo;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {
    //1. Controller 어노테이션을 달면, 이 컨트롤러가 스프링 컨테이너에 들어가게 되고, 스프링이 관리함.

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        //2. 생성자에 Autowired 어노테이션을 달면, 스프링 컨테이너에 있는 memverService를 가져다 연결을 시켜줌.
        //   Service와 하위의 Repository가 컨테이너에 올라가 있지 않으면, 에러가 남
        //= 생성자에 Autowired를 쓰면, 생성자로 생성이 될때 스프링 빈에 등록되어 있는 Service, Repository를 가져다 넣어줌
        //  이것을 의존관계 주입, 의존관계 설정이라고 함
        //컴포넌트 스캔 방법으로 스프링 빈을 등록한 방법임(@컨트롤러, @서비스, @레파지토리를 사용하는 방법)
        //컨트롤러, 서비스, 레파지토리 코드를 보면 컴포넌트로 등록하는 방법
        //+ 스프링은 컨테이너에 스프링 빈을 등록할때, 기본으로 싱글톤으로 등록한다. 같은 빈이면 모두 같은 인스턴스 임.
        this.memberService = memberService;
    }

    //3. DI에는 필드주입, 생성자주입, setter주입이 있음.
    //필드 주입은 권장하지 않음, 바꿀 수 있는 방법이 제한적임.
    //생성자 주입을 많이 사용함
    //상황에 따라 구현 클래스를 변경해야 할때, 자바코드를 통한 빈 생성 방법을 사용함


    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form){
        MemberVo memberVo = new MemberVo();
        memberVo.setName(form.getName());
        memberService.join(memberVo);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){
        List<MemberVo> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
