/*
 *   Copyright (c) 2020 The Sellics Assignment.
 *   All Rights Reserved.
 *
 */

package com.sellics.search.exception;

import com.sellics.search.model.ErrorDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

/**
 * Handles the exceptions across the application
 *
 * @author Sujata Khochede
 * @since 12-FEB-2020
 *
 */
@ControllerAdvice
public class ExceptionControllerAdvice {

    Logger logger = LoggerFactory.getLogger(ExceptionControllerAdvice.class);

    /**
     * Handles java.lang.Exception.class
     * @param exception exception
     * @return Response json of ErrorDetail
     */
    @ExceptionHandler(java.lang.Exception.class)
    public ResponseEntity<ErrorDetail> exceptionHandler(java.lang.Exception exception) {

        logger.error(exception.getMessage(), exception);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ErrorDetail.Builder()
                        .timeStamp(LocalDateTime.now())
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .message(exception.getMessage())
                        .exception(exception.getCause().toString())
                        .build()
        );
    }
}
