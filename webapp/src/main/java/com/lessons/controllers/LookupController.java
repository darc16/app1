package com.lessons.controllers;

import com.lessons.model.LookupDTO;
import com.lessons.services.LookupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.util.List;

@Controller("com.lessons.controllers.LookupController")
public class LookupController {
    private static final Logger logger = LoggerFactory.getLogger(LookupController.class);

    @Resource
    private LookupService lookupService;

    /*************************************************************************
     * REST endpoint /api/lookups/{lookupType}/{orderBy}
     *               /api/lookups/{lookupType}
     *
     * @return list of LookupDTO objects for the given category name
     *************************************************************************/
    @RequestMapping(value = {"/api/lookups/{lookupType}/{orderBy}",
            "/api/lookups/{lookupType}"}, method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> getLookupsWithType(@PathVariable(name="lookupType")               String aLookupType,
                                                @PathVariable(name="orderBy", required=false)  String aOrderBy) {
        logger.debug("getLookupsWithType() started.   aLookupType={}", aLookupType);

        if (! lookupService.doesLookupTypeExist(aLookupType) ) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .contentType(MediaType.TEXT_PLAIN)
                    .body("The pass-in lookup type does not exist: " + aLookupType);
        }

        List<LookupDTO> lookupDTOS = lookupService.getLookupsWithType(aLookupType, aOrderBy);

        // Return the list of LookupDTO objects back to the front-end
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(lookupDTOS);
    }
}