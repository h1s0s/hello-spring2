package hello.hellospring.repository;

import hello.hellospring.domain.MemberVo;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

public class JdbcMemberRepository implements MemberRepository {

    private final DataSource dataSource;

    public JdbcMemberRepository(DataSource dataSource){
        this.dataSource = dataSource;
    }
    @Override
    public MemberVo save(MemberVo memberVo) {
        String sql = "insert into member(name) values(?)";

        Connection conn = null;

    }

    @Override
    public Optional<MemberVo> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<MemberVo> findByName(String name) {
        return Optional.empty();
    }

    @Override
    public List<MemberVo> findAll() {
        return null;
    }
}
