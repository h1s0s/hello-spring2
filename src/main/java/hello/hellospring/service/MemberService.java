package hello.hellospring.service;

import hello.hellospring.domain.MemberVo;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public class MemberService {


    private MemberRepository memberRepository = new MemoryMemberRepository();

    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }
    /**
     * 회원 가입
     * @param memberVo
     * @return Long
     */
    public Long join(MemberVo memberVo){
        //비즈니스로직 + 같은 이름의 중복 회원 X
        validateDuplicateMember(memberVo);//중복회원 검증
        memberRepository.save(memberVo);
        return memberVo.getId();
    }

    private void validateDuplicateMember(MemberVo memberVo) {//ctrl+t 리팩토링 관련 단축키 염
        memberRepository.findByName(memberVo.getName())//comand+op+v
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     * 전체 회원 조회
     * @return
     */
    public List<MemberVo> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<MemberVo> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
