package com.lessons.services;
import com.lessons.model.IndicatorCountDTO;
import com.lessons.model.IndicatorDTO;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.List;


@Service
public class IndicatorService {

    @Resource
    private DataSource dataSource;

    public IndicatorCountDTO getIndicatorCountDTO() {
        String sql = "Select count(*) from indicators";
        JdbcTemplate jt = new JdbcTemplate(this.dataSource);
        SqlRowSet rs = jt.queryForRowSet(sql);
        int count = 0;
        if(rs.next()){
            count = rs.getInt("count");
        }
        return new IndicatorCountDTO(count);
    }

    public List<IndicatorDTO> getAllIndicators() {
        BeanPropertyRowMapper rowMapper = new BeanPropertyRowMapper(IndicatorDTO.class);

        String sql = "Select id, value, created_date from indicators";
        JdbcTemplate jt = new JdbcTemplate(this.dataSource);

        return jt.query(sql, rowMapper);

    }
}
