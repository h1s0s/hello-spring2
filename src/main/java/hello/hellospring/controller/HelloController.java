package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data","hello!!");
        return "hello";//리턴값으로 문자를 반환하면 뷰리졸버가 화면을 찾아서 처리(resources/templates/*.html)
        //tip! spring-boot-devtools 라이브러리를 사용하면, 서버 재시작 없이 View 파일 변경 가능

    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model){
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody//그대로 데이터를 문자열로 return함, html코드로 return하는게 아님.
    public String helloString(@RequestParam("name") String name){
        return "hello"+name;
    }

    //스프링에서 말하는 API 방식은 객체를 반환하는 것
    @GetMapping("hello-api")
    @ResponseBody//그대로 데이터를 문자열로 return함, html코드로 return하는게 아님.
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello;//객체를 json 방식으로 화면에 띄움, 최근에는 거의 json 방식 사용, ResponseBody는 json방식이라고 보면 됨
        //'@ResoponseBody'
        //뷰리졸버 대신에 HttpMessageConveter가 동작
        // return 문자열 : StringHttpMessageConverter라는 라이브러리가 동작해 브라우저에 응답
        // return 객체 : MappingJackson2HttpMessageConverter라는 라이브러리 동작해 json 타입으로 변환 된 후 브라우저에 응답

    }

    static class Hello {
        private String name;

        //인텔리제이에서는 컨트롤+엔터 치면 게터세터 자동생성(프로퍼티 접근 방식) 가능
        //커맨드+엔터 치면 자동완성
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
