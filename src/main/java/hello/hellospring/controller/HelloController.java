package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data","hello!!");
        return "hello";//리턴값으로 문자를 반환하면 뷰리졸버가 화면을 찾아서 처리(resources/templates/*.html)
        //spring-boot-devtools 라이브러리를 사용하면, 서버 재시작 없이 View 파일 변경 가능

    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(name = "name") String name, Model model){
        model.addAttribute("name", name);
        return "hello-template";
    }
}
