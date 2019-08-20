package com.lessons.controllers;
import com.lessons.model.IndicatorCountDTO;
import com.lessons.services.IndicatorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.annotation.Resource;

@Controller
public class IndicatorsController {

    @Resource
    private IndicatorService indicatorService;

    @RequestMapping(value = "/api/indicator/count", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> getIndicatorCount() {

        IndicatorCountDTO indicator_count = indicatorService.getIndicatorCount();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(indicator_count);
    }
}

