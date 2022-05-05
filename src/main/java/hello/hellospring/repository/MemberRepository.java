package hello.hellospring.repository;

import hello.hellospring.domain.MemberVo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    MemberVo save(MemberVo memberVo);
    Optional<MemberVo> findById(Long id);
    Optional<MemberVo> findByName(String name);
    List<MemberVo> findAll();
}
