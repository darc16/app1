package com.lessons.services;

import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("com.lessons.services.HealthChecks")
public class HealthChecks {

    @Resource
    private AsyncHttpClient asyncHttpClient;

    @Value("${elasticSearch.url}")
    private String elasticSearchUrl;

    public boolean doAllIndiciesExist() throws Exception{
        Response searchResults = asyncHttpClient.prepareGet(elasticSearchUrl + "/_cat/indices")
                .setHeader("accepts", "application/json")
                .setHeader("contentType","application/json")
                .execute()
                .get();

        String res = searchResults.getResponseBody();

        if(res.contains("report")){
            return true;
        }

        return false;
    }
}
