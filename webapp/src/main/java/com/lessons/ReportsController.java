package com.lessons;

import com.lessons.services.DashboardDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.websocket.server.PathParam;

@Controller("com.lessons.ReportsController")
public class ReportsController {
    private static final Logger logger = LoggerFactory.getLogger(ReportsController.class);

    @Resource
    private DashboardDao dashboardDao;


    /********************************************** ***************************
     * getDateTime()
     * @return JSON string that holds the date/time
     *************************************************************************/
    @RequestMapping(value = "/api/reports/{reportId}", method = RequestMethod.GET, produces = "application/json")

    public ResponseEntity<?> getDateTime(@PathVariable(name = "reportId") int reportId) {

        // Get the date/time from the database
        String sDateTime = dashboardDao.getReportInformation(reportId);

        // Return the date/time string as plain-text
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.TEXT_PLAIN)
                .body(sDateTime);
    }
}