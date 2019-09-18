package com.lessons.controllers;


import com.lessons.services.DashboardDao;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Controller("com.lessons.controllers.EnvController")
public class EnvController {
    private static final Logger logger = LoggerFactory.getLogger(EnvController.class);

    @Resource
    private DashboardDao dashboardDao;

    public EnvController(){
        logger.debug("Env Controller Constructor called");
    }

    @PostConstruct
    public void postConstruct() {
        logger.debug("Env Controller Post Constructor called");
    }

    @Value("${network.name}")
    private String networkName;

    @Value("${hdfs.enabled}")
    private Boolean hdfsEnabled;

    @Value("${show.classified.banner}")
    private Boolean showClassifiedBanner;


    /*************************************************************************
     * getDateTime()
     * @return JSON string that holds the date/time
     *************************************************************************/
    @RequestMapping(value = "/api/env", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> getEnvironmentProperties() {
        logger.debug("getDashboardDetails() started.");

        Map<String, Object> envProperties = new HashMap<>();

       envProperties.put("network.name", networkName);
       envProperties.put("hdfs.enabled", hdfsEnabled);
       envProperties.put("show.classified.banner", showClassifiedBanner);

        // Return the date/time string as plain-text
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(envProperties);
    }



}