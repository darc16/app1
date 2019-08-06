package com.lessons.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.sql.DataSource;

@Service
public class DashboardDao
{
    private static final Logger logger = LoggerFactory.getLogger(DashboardDao.class);

    @Resource
    private DataSource dataSource;

    public DashboardDao()
    {
        logger.debug("DashboardDao() Constructor called");
    }


    public String getDatabaseTime()
    {
        logger.debug("getDatabaseTime() started.");

        // Run a SQL query to get the current date time
        String sql = "Select NOW()";
        JdbcTemplate jt = new JdbcTemplate(this.dataSource);
        String sDateTime = jt.queryForObject(sql, String.class);
        logger.debug("Database Time is {}", sDateTime);

        return sDateTime;
    }

    public String getReportInformation(int reportId) {

        String sql = "Select * from reports where id = " + reportId;
        JdbcTemplate jt = new JdbcTemplate(this.dataSource);
        String reportInfo = jt.queryForObject(sql, String.class);
        logger.debug("Database Time is {}", reportInfo);

        return reportInfo;
    }
}