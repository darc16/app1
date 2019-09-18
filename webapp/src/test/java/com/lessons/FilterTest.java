package com.lessons;

import com.lessons.filter.FilterParams;
import com.lessons.filter.FilterService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertTrue;

@SpringBootTest
@RunWith(SpringRunner.class)
public class FilterTest {

    private static final Logger logger = LoggerFactory.getLogger(IndicatorServiceTest.class);

    @Resource
    FilterService filterService;

    @Test
    public void testEquals(){
        List<String> filters = new ArrayList<>();
        filters.add("ID~EQUALS~454");

        FilterParams fp = filterService.getFilterParamsForFilters(filters);

        assertTrue("wrong size for the map", fp.getBindVars().size() == 1);
        String sql = fp.getSql().trim();
        assertTrue("sql is wrong", sql.equalsIgnoreCase("id = :bindvar2"));

        logger.debug("test finished");
    }

    @Test
    public void testIn(){
        List<String> filters = new ArrayList<>();
        filters.add("ID~IN~454~22");

        FilterParams fp = filterService.getFilterParamsForFilters(filters);

        assertTrue("wrong size for the map", fp.getBindVars().size() == 1);
        String sql = fp.getSql().trim();
        assertTrue("sql is wrong", sql.equalsIgnoreCase("id in (:bindvar2)"));

        logger.debug("test finished");
    }

    @Test
    public void testGreater(){
        List<String> filters = new ArrayList<>();
        filters.add("ID~Greater~454");

        FilterParams fp = filterService.getFilterParamsForFilters(filters);

        assertTrue("wrong size for the map", fp.getBindVars().size() == 1);
        String sql = fp.getSql().trim();
        assertTrue("sql is wrong", sql.equalsIgnoreCase("id > ::bindvar2::Integer"));

        logger.debug("test finished");
    }

    @Test
    public void testBetween(){
        List<String> filters = new ArrayList<>();
        filters.add("date~Between~2017~2018");

        FilterParams fp = filterService.getFilterParamsForFilters(filters);

        assertTrue("wrong size for the map", fp.getBindVars().size() == 2);
        String sql = fp.getSql().trim();
        assertTrue("sql is wrong", sql.equalsIgnoreCase("date between :bindvar2 and :bindvar3"));

        logger.debug("test finished");
    }
}
