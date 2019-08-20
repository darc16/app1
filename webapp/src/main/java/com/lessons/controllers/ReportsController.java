package com.lessons.controllers;

import com.lessons.model.ShortReport;
import com.lessons.services.DashboardDao;
import com.lessons.services.ReportsDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class ReportsController {

    @Resource
    private DashboardDao dashboardDao;

    @Resource
    private ReportsDao reportsDao;

    private static final Logger logger = LoggerFactory.getLogger(DashboardController.class);


    @RequestMapping(value = "/api/reports/approve", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> approveReport(@RequestParam(name = "id") Integer reportId, @RequestParam(name = "approved") Boolean approve) {
        logger.debug("getDashboardDetails() started.");

        if(!reportsDao.doesReportExist(reportId)){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Bad Request, report " + reportId + " not found in database");
        }
        // Get the date/time from the database
         reportsDao.approveReport(reportId, approve);

        // Return the date/time string as plain-text
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("");
    }

    @RequestMapping(value = "/api/reports/short", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> getShortReports() {

        List<ShortReport> shortReports = reportsDao.getShortReportsBetter();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(shortReports);
    }
}
