package com.lessons.controllers;
import com.lessons.model.IndicatorCountDTO;
import com.lessons.model.IndicatorDTO;
import com.lessons.services.IndicatorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.annotation.Resource;
import java.util.List;

@Controller
public class IndicatorsController {

    @Resource
    private IndicatorService indicatorService;

    @RequestMapping(value = "/api/indicator/count", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> getIndicatorCount() {
        IndicatorCountDTO indicator_count = indicatorService.getIndicatorCountDTO();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(indicator_count);
    }

    @RequestMapping(value = "/api/indicator/all", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> getAllIndicators() {
        List<IndicatorDTO> allIndicators = indicatorService.getAllIndicators();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(allIndicators);
    }

    @RequestMapping(value = "/api/bad", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> getAllErrors() {
        int i = 1;
        if(i==1) {
            throw new RuntimeException("Error");
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("");
    }


}

