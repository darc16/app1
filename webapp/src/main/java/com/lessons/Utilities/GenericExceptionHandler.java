package com.lessons.Utilities;

import com.lessons.services.ReportsDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

@ControllerAdvice  // this annotation is needed to make it work
public class GenericExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(ReportsDao.class);

public GenericExceptionHandler(){
    logger.debug("constructor");
}

@PostConstruct
public void postConstruct(){
    logger.debug("post construct");
}

    @Value("${development.mode}")
    private Boolean developmentMode;


    @ExceptionHandler
    public ResponseEntity<?> handleException(Exception exception){
//        this holds the details about the request that caused the error
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        if (null != request){
            logger.error("Request to " + request.getRequestURI() + "caused an exception", exception);
        } else {
            logger.debug("Error", exception);
        }
        if(developmentMode){
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(exception.getLocalizedMessage());
        } else {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Something went wrong please contact an admin");
        }
    }
}
