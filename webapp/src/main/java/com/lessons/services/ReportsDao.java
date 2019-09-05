package com.lessons.services;

import com.lessons.filter.FilterParams;
import com.lessons.filter.FilterService;
import com.lessons.model.ShortReport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.print.attribute.HashPrintJobAttributeSet;
import javax.sql.DataSource;
import javax.sql.RowSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



@Service
public class ReportsDao
{

    @Value("${development.mode}")
    private Boolean developmentMode;

    @Value("${network.name}")
    private String networkName;

    public ReportsDao(){
        logger.debug("Constructor started {}", developmentMode);
    }

    @PostConstruct
    public void init(){
        logger.debug("Post Constructor started {}", developmentMode);
        if(!networkName.equalsIgnoreCase("NIPR")){
            throw new RuntimeException("network name is wrong, try again you passed in: " + networkName);
        }
    }

    private static final Logger logger = LoggerFactory.getLogger(ReportsDao.class);

    @Resource
    private DataSource dataSource;

    @Resource
    private FilterService filterService;

    public void approveReport(Integer id, Boolean approve)
    {
        logger.debug("getDatabaseTime() started.");

        // Run a SQL query to get the current date time


        String sql = "UPDATE reports SET  reviewed = ? where id = ? returning *";

        JdbcTemplate jt = new JdbcTemplate(this.dataSource);
            Map<String, Object> rowMap = jt.queryForMap(sql, approve, id);


        sql = "Insert into reports_aud (rev, rev_type, id, description, version, reviewed) values(:rev, :rev_type, :id, :description, :version, :reviewed)";

            Map<String, Object> sqlMap = new HashMap<>() ;

            sqlMap.put("id", rowMap.get("id"));
            sqlMap.put("description", rowMap.get("description"));
            sqlMap.put("version", rowMap.get("version"));
            sqlMap.put("reviewed", rowMap.get("reviewed"));
            sqlMap.put("rev", getNextSeqVal());
            sqlMap.put("rev_type", 1);

        NamedParameterJdbcTemplate np = new NamedParameterJdbcTemplate(this.dataSource);
        np.update(sql, sqlMap);

    }

    public boolean doesReportExist(Integer reportId) {
        String sql = "Select id from reports where id = ?";

        JdbcTemplate jt = new JdbcTemplate(this.dataSource);
        SqlRowSet rs = jt.queryForRowSet(sql, reportId);

        return rs.next();

    }

    public int getNextSeqVal (){
        String sql = "select nextVal('seq_table_ids')";
        JdbcTemplate jt = new JdbcTemplate(this.dataSource);
        int seq = jt.queryForObject(sql, Integer.class);
        return seq;
    }

    public List<ShortReport> getShortReports() {

        String sql ="Select id, description, display_name, version from reports";
        JdbcTemplate jt = new JdbcTemplate(this.dataSource);
        SqlRowSet rs = jt.queryForRowSet(sql);
        List<ShortReport> shortReports = new ArrayList<ShortReport>();
        while(rs.next()){
            ShortReport shortRep = new ShortReport(rs.getInt("id"), rs.getString("description"), rs.getString("display_name"));
            shortReports.add(shortRep);
        }
        return shortReports;
    }


    public List<ShortReport> getShortReportsBetter() {

        BeanPropertyRowMapper rowMapper = new BeanPropertyRowMapper(ShortReport.class);

        String sql ="Select id, description, display_name from reports";
        JdbcTemplate jt = new JdbcTemplate(this.dataSource);

        List<ShortReport> shortReports = jt.query(sql, rowMapper);

        return shortReports;
    }

    public List<ShortReport> getFilteredReports(List<String> filters) {
        BeanPropertyRowMapper rowMapper = new BeanPropertyRowMapper(ShortReport.class);

        // get sql where clause and param map for query
        FilterParams filterParams = filterService.getFilterParamsForFilters(filters);
        String sql = "select id, description, display_name from reports";
        String whereClause = filterParams.getSql();
        List<ShortReport> shortReports;

        // if a where clause is returned add it to the sql
        if (whereClause != null) {
            sql = sql + " where " + whereClause;
            NamedParameterJdbcTemplate np = new NamedParameterJdbcTemplate(this.dataSource);
            shortReports = np.query(sql, filterParams.getBindVars(), rowMapper);
        } else {
            JdbcTemplate jt = new JdbcTemplate(this.dataSource);
            shortReports = jt.query(sql, rowMapper);
        }

        // return list of ShortReport DTO objects
        return shortReports;
    }
}