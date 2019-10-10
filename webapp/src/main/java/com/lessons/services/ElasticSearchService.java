package com.lessons.services;

import com.lessons.model.SearchDTO;

import org.apache.commons.lang3.StringUtils;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


@Service
public class ElasticSearchService {

    @Resource
    private AsyncHttpClient asyncHttpClient;

    @Value("${elasticSearch.url}")
    private String elasticSearchUrl;

    public String runSearch(String rawQuery) throws Exception {
        Response searchResults = asyncHttpClient.preparePost(elasticSearchUrl + "/reports/_search?pretty=true&q=" + rawQuery)
                .setHeader("accepts", "application/json")
                .setHeader("contentType","application/json")
                .execute()
                .get();

        return searchResults.getResponseBody();
    }

    public String runAdvancedSearch(SearchDTO searchDTO) throws Exception {
        List<String> formattedFilters = new ArrayList<>();
        if(searchDTO.getFilters() != null && !searchDTO.getFilters().isEmpty()) {
            for (String filter : searchDTO.getFilters()) {
                String[] splitFilter = filter.split(":");
                formattedFilters.add(
                        "{\"term\" : {\"" + splitFilter[0] + "\":\"" + splitFilter[1] + "\"}}"
                );
            }
        }
        String searchJson = "{\n" +
                "   \"query\":{\n" +
                "       \"bool\":{\n" +
                "           \"must\": [\n" +
                "               {\"query_string\": {\n" +
                "                  \"query\": \"" + searchDTO.getRawQuery().replace("\"", "\\\"") + "\"\n" +
                "               }\n" +
                "                    }],\n" +
                "                    \"filter\":\n" + formattedFilters.toString() +
                "       }\n" +
                "   }\n" +
                "}";

        Response searchResults = asyncHttpClient.preparePost(elasticSearchUrl + "/reports/_search?pretty=true")
                .setHeader("accepts", "application/json")
                .setHeader("contentType","application/json")
                .setBody(searchJson)
                .execute()
                .get();

        return searchResults.getResponseBody();
    }

    public boolean isDtoValid(SearchDTO searchDTO){
        if(searchDTO.getFilters() == null ){
            return false;
        }
        if(searchDTO.getRawQuery() != null && StringUtils.countMatches(searchDTO.getRawQuery(),"\"") > 2 ){
            return false;
        } else {
            return true;
        }
    }

}
