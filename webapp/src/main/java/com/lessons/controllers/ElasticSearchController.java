package com.lessons.controllers;
import com.lessons.model.SearchDTO;
import com.lessons.services.ElasticSearchService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

@Controller
public class ElasticSearchController {

    @Resource
    private ElasticSearchService elasticSearchService;

    @RequestMapping(value = "/api/search/", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> runSearch(@RequestParam(name="query", required=false) String rawQuery) throws Exception {
        String searchResults = elasticSearchService.runSearch(rawQuery);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(searchResults);
    }

    @RequestMapping(value = "/api/searchAdvanced", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> runAdvSearch(@RequestBody SearchDTO searchDTO) throws Exception {
        if(elasticSearchService.isDtoValid(searchDTO)){
            String searchResults = elasticSearchService.runAdvancedSearch(searchDTO);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(searchResults);
        } else {
            return ResponseEntity
                    .status(HttpStatus.I_AM_A_TEAPOT)
                    .body("Bad request");
        }


    }

}

