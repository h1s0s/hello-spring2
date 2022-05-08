package hello.hellospring.repository;

import hello.hellospring.domain.MemberVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class JdbcTemplateMemberRepository implements MemberRepository {

    private final JdbcTemplate jdbcTemplate;

    //생성자가 딱 1개일 경우 Autowired 생략 가능
    public JdbcTemplateMemberRepository(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public MemberVo save(MemberVo memberVo) {
        //심플jdbc인서트를 사용하면 쿼리를 짤 필요가 없음
        //도큐먼트 보고 더 공부하기
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("member").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", memberVo.getName());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        memberVo.setId(key.longValue());
        return memberVo;
    }

    @Override
    public Optional<MemberVo> findById(Long id) {
        //디자인 패턴중 템플릿메서드 패턴을 이용해서, jdbcTemplate이라고 함
        List<MemberVo> result = jdbcTemplate.query("select * from member where id = ?", memberVoRowMapper(), id);
        return result.stream().findAny();
    }

    @Override
    public Optional<MemberVo> findByName(String name) {
        List<MemberVo> result = jdbcTemplate.query("select * from member where name = ?", memberVoRowMapper(), name);
        return result.stream().findAny();
    }

    @Override
    public List<MemberVo> findAll() {
        return jdbcTemplate.query("select * from member", memberVoRowMapper());
    }

    private RowMapper<MemberVo> memberVoRowMapper() {
        return (rs, rowNum) -> {//람다 스타일
            MemberVo memberVo = new MemberVo();
            memberVo.setId(rs.getLong("id"));
            memberVo.setName(rs.getString("name"));
            return memberVo;
        };
    }
}
