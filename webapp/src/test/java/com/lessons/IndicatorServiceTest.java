package com.lessons;

import com.lessons.services.IndicatorService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IndicatorServiceTest {
        private static final Logger logger = LoggerFactory.getLogger(IndicatorServiceTest.class);

        @Resource
        private IndicatorService indicatorService;

        @Test
    public void testCase1(){

        }
}
