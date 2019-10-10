package com.lessons;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.concurrent.ThreadLocalRandom;

@SpringBootTest
@RunWith(SpringRunner.class)



public class AddTestRecords {

    @Resource
    private DataSource dataSource;

private static final Logger logger = LoggerFactory.getLogger(IndicatorServiceTest.class);

    @Test
    public void addStuff(){
        logger.debug("it worked!!!");
        int total = 50000;
        for(int i=1; i <=total; i++) {
           createReportRecord(generateItemId());
            if((i %100) == 0){
                logger.debug("{} of {}", i, total);
            }
        }
    }


private int getRandomNumberBetween(int min, int max){
    int randomNumber = ThreadLocalRandom.current().nextInt(min,max+1);

    return randomNumber;
}

private boolean getRandomBoolean(){
    int bool = getRandomNumberBetween(1,2);
    if(bool == 1){
        return false;
    } else {
        return true;
    }
}

    private void createIndicators(int totalIndicators, int reportId){
        JdbcTemplate jt = new JdbcTemplate(dataSource);
        String indicatorInsertValues = "";
        String lriInsertValues = "";
        String auditInsertValues = "";
        for(int i=1; i <=totalIndicators; i++){
            int indicatorId = generateItemId();
            int indType = getRandomNumberBetween(1,5);
            String indValue = getRandomIPAddress();
            indicatorInsertValues = indicatorInsertValues + "(" + indicatorId + ",'" + indValue + "'," + indType + "),";
            lriInsertValues = lriInsertValues + "(" + reportId + "," + indicatorId + "),";
            auditInsertValues = auditInsertValues + "(" + indicatorId + ",'" + indValue + "'," + generateItemId() + "," + 0 + ",'dave_cavins'," + indType + ",now()),";
        }
        String sql = "Insert into indicators (id, value, ind_type) values " + StringUtils.chop(indicatorInsertValues);
        jt.update(sql);

        sql = "Insert into link_reports_indicators (report, indicator) values " + StringUtils.chop(lriInsertValues);
        jt.update(sql);

        sql = "Insert into indicators_aud (id, value, rev, rev_type, user_name, ind_type, timestamp) values " + StringUtils.chop(auditInsertValues);
        jt.update(sql);
    }

private void createReportRecord(int reportId){
    JdbcTemplate jt = new JdbcTemplate(dataSource);
    boolean isCustom = getRandomBoolean();
    String description = "Everything is awesome" + reportId;
    String displayName = "MyReportName" + reportId + ".txt";
    String sql = "Insert into reports (id, display_name, is_custom_report, description) values (?,?,?,?)";
    jt.update(sql, reportId, displayName, isCustom, description);
    createIndicators(getRandomNumberBetween(1,100),reportId);
}

private Integer generateItemId(){
    JdbcTemplate jt = new JdbcTemplate(dataSource);
    String sql = "select nextval('seq_table_ids')";
    return jt.queryForObject(sql, Integer.class);
}


private String getRandomIPAddress(){
        int octOne = getRandomNumberBetween(1, 255);
        int octTwo = getRandomNumberBetween(0, 255);
        int octThree = getRandomNumberBetween(0, 255);
        int octFour = getRandomNumberBetween(1, 255);

        return octOne + "." + octTwo + "." + octThree + "." + octFour;
}

}