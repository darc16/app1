package com.lessons.controllers;

import com.lessons.filter.FilterService;
import com.lessons.model.FilterDTO;
import com.lessons.model.ReportStats;
import com.lessons.model.ShortReport;
import com.lessons.models.AddReportDTO;
import com.lessons.services.DashboardDao;
import com.lessons.services.ReportsDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
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

    @Resource
    private FilterService filterService;

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

    @RequestMapping(value = "/api/reports/stats", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> getReportStats() {

        List<ReportStats> stats = reportsDao.getReportStats();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(stats);
    }

    @RequestMapping(value = "/api/reports/filtered", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> getFilteredReports(@RequestParam(name="filters", required=false) List<String> filters) {

        logger.debug("filters {}", filters);

        if(!filterService.areFiltersValid(filters)){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Bad Request, invalid filters");
        };

        List<ShortReport> reports = reportsDao.getFilteredReports(filters);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(reports);
    }



    @RequestMapping(value = "/api/reports/morefiltered", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> getMoreFilteredReports(@RequestBody FilterDTO filters) {

        logger.debug("filters {}", filters);

        if(filters.getPageSize() == null || filters.getPageSize() < 0){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Bad Request, no pagesize");
        }
        if(filters.getOffset() == null || filters.getOffset() < 1 || filters.getOffset() > 10000){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Bad Request, no Take Off");
        }



        return ResponseEntity
                .status(HttpStatus.OK)
                .body("");
    }


    /*************************************************************************
     * REST endpoint /api/reports/add
     *
     * @return nothing
     *************************************************************************/
    @RequestMapping(value = "/api/reports/add", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> addReport(@RequestBody AddReportDTO aAddReportDTO) {
        logger.debug("addReport() started.");

        // Simulate adding a report to the system

        return ResponseEntity
                .status(HttpStatus.OK)
                .body("");
    }

}
