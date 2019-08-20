package com.lessons.services;
import com.lessons.model.IndicatorCountDTO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.sql.DataSource;


@Service
public class IndicatorService {

    @Resource
    private DataSource dataSource;

    public IndicatorCountDTO getIndicatorCount() {
        String sql = "Select count(*) from indicators";
        JdbcTemplate jt = new JdbcTemplate(this.dataSource);
        Integer rs = jt.queryForObject(sql, Integer.class);

        return new IndicatorCountDTO(rs);
    }
}
