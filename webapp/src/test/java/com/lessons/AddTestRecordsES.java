//package com.lessons;
//
//
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.PropertyNamingStrategy;
//import org.asynchttpclient.AsyncHttpClient;
//import org.asynchttpclient.Response;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import javax.annotation.Resource;
//import javax.sql.DataSource;
//import java.util.List;
//import java.util.Map;
//
//@SpringBootTest
//@RunWith(SpringRunner.class)
//
//public class AddTestRecordsES {
//
//    @Resource
//    private AsyncHttpClient asyncHttpClient;
//
//    @Resource
//    private DataSource dataSource;
//
//
//
//    @Value("${elasticSearch.url}")
//    private String elasticSearchUrl;
//
//    private static final Logger logger = LoggerFactory.getLogger(AddTestRecordsES.class);
//
//    @Test
//    public void insertTestRecordsIntoES() throws Exception{
//        logger.debug("something");
//
//        List<ShortReport> reports = reportsDao.getAllShortReports();
//
////        String recordsToInsert ="{\"index\": {\"_index\":\"reports\", \"_type\":\"record\"}}\n" +
////                "{\"priority\":\"low\", \"actors\":\"Elastic Search\", \"description\":\"I like it alot more A paragraph is a self-contained unit of a discourse in writing dealing with a particular point or idea. A paragraph consists of one or more sentences. Though not required by the syntax of any language, paragraphs are usually an expected part of formal writing, used to organize longer prose\", " +
////                "\"id\":55, \"ip\":\"1.2.3.4\", \"created_date\":\"1998-04-01\", \"is_completed\":true, \"country\":\"china\"}\n";
//
//        ObjectMapper omapper = new ObjectMapper();
//        omapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
//        StringBuilder sb = new StringBuilder();
//        for(ShortReport report : reports){
//            sb.append("{\"index\": {\"_index\":\"reports\", \"_type\":\"record\", \"_id\":"+ report.getId() +"}}\n" );
//            sb.append(omapper.writeValueAsString(report) + "\n");
//        }
//
//        Response submitResults = asyncHttpClient.preparePost(elasticSearchUrl + "/_bulk")
//                .setHeader("accepts", "application/json")
//                .setHeader("contentType","application/json")
//                .setBody(sb.toString())
//                .execute()
//                .get();
//
//        logger.debug(submitResults.getResponseBody());
//
//        Map<String, Object> resulteMap = omapper.readValue(submitResults.getResponseBody(), new TypeReference<Map<String, Object>>(){});
//
//        boolean errors = (boolean)resulteMap.get("errors");
//
//        if(resulteMap.size() > 0 && errors || submitResults.getStatusCode() != 200){
//            logger.debug("errors happened");
//        }
//    }
//
//}
