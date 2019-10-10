package com.lessons.config;

import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class HttpClientConfig {

    private static final Logger logger = LoggerFactory.getLogger(HttpClientConfig.class);


    public HttpClientConfig(){
        logger.debug("http config started.");
    }

    @Bean
    public AsyncHttpClient asyncHttpClient() {
        logger.debug("http hou; started.");
        return new DefaultAsyncHttpClient();
    }
}