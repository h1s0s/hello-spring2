package hello.hellospring.repository;

import hello.hellospring.domain.MemberVo;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, MemberVo> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public MemberVo save(MemberVo memberVo) {
        memberVo.setId(++sequence);
        store.put(memberVo.getId(), memberVo);
        return memberVo;
    }

    @Override
    public Optional<MemberVo> findById(Long id) {
        return Optional.ofNullable(store.get(id));
        //Optional : null값이 들어 올 수 있을때 감쌈
    }

    @Override
    public Optional<MemberVo> findByName(String name) {
        return store.values().stream()
                .filter(memberVo -> memberVo.getName().equals(name))
                .findAny();
        //java8의 람다 공부하기
    }

    @Override
    public List<MemberVo> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();
    }
}
