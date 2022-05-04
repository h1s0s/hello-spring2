package hello.hellospring.repository;

import hello.hellospring.domain.MemberVo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach//테스트가 끝날때마다 이게 실행됨
    public void afterEach(){
        repository.clearStore();//저장소를 지우는 메소드
    }

    @Test//돌리면 이 메소드가 실행됨
    public void save() {
        MemberVo memberVo = new MemberVo();
        memberVo.setName("spring");

        repository.save(memberVo);

        MemberVo result = repository.findById(memberVo.getId()).get();
        //Assertions.assertEquals(memberVo, result);//똑같은지 확인해줌.
        assertThat(memberVo).isEqualTo(result);
    }

    @Test
    public void findByName() {
        MemberVo member1 = new MemberVo();
        member1.setName("spring1");
        repository.save(member1);

        MemberVo member2 = new MemberVo();//shift+f6 복사했을때 이름 한번에 수정 가능
        member2.setName("spring2");
        repository.save(member2);

        MemberVo result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);//같은 객체인지 비교하는 메소드
    }

    @Test
    public void findAll() {
        MemberVo member1 = new MemberVo();
        member1.setName("spring1");
        repository.save(member1);

        MemberVo member2 = new MemberVo();
        member2.setName("spring2");
        repository.save(member2);

        List<MemberVo> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }

    //Tip. 메소드에 순서가 정해져있지 않고, 메모리에 저장 하기떄문에 테스트가 끝나고 나면, 클리어를 해줘야 함.
    //테스트 주도 개발 TDD, 테스트를 먼저 만들고 구현하는 개발 기법
}
