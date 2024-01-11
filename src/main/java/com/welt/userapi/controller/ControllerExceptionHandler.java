package com.welt.userapi.controller;

import com.welt.userapi.exceptions.BaseResponse;
import com.welt.userapi.exceptions.UserException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(ControllerExceptionHandler.class);

    @ExceptionHandler(UserException.class)
    protected ResponseEntity<Object> handleBusinessExceptions(UserException userException){
        logger.info("User error. Message: {}", userException.getMessage());
        final BaseResponse response = new BaseResponse(userException.getErrorType());
        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
    }
}
