/*
 *   Copyright (c) 2020 The Sellics Assignment.
 *   All Rights Reserved.
 *
 */

package com.sellics.search.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

/**
 * Error response model
 *
 * @author Sujata Khochede
 * @since 12-FEB-2020
 */
public class ErrorDetail {

    /**
     * the relevant error code
     */
    @JsonProperty("code")
    private String code;

    /**
     * the relevant error message
     */
    @JsonProperty("message")
    private String message;

    /**
     * the relevant Http Status of the returned Response
     */
    private HttpStatus status;

    /**
     * current timestamp
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;

    /**
     * the name of the thrown Exception
     */
    private String exception;

    /**
     * Inner class for Error detail builder
     *
     */
    public static class Builder {
        private String code;
        private String message;
        private HttpStatus status;
        private LocalDateTime timestamp;
        private String exception;

        public Builder() {
        }

        public Builder timeStamp(LocalDateTime time) {
            this.timestamp = time;
            return this;
        }

        public Builder status(HttpStatus httpStatus) {
            this.status = httpStatus;
            return this;
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public Builder exception(String exception) {
            this.exception = exception;
            return this;
        }

        public ErrorDetail build() {
            ErrorDetail errorDetail = new ErrorDetail();
            errorDetail.timestamp = this.timestamp;
            errorDetail.status = this.status;
            errorDetail.message = this.message;
            errorDetail.exception = this.exception;
            return errorDetail;
        }


    }

    private ErrorDetail() {
    }


}
