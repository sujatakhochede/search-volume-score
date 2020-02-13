/*
 *   Copyright (c) 2020 The Sellics Assignment.
 *   All Rights Reserved.
 *
 */

package com.sellics.search.web;

import com.sellics.search.model.ErrorDetail;
import com.sellics.search.model.SearchVolume;
import com.sellics.search.service.SearchVolumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import java.time.LocalDateTime;

/**
 * Controller class for estimate search-volume service
 *
 * @author Sujata Khochede
 * @since 12-FEB-2020
 */
@RestController
@RequestMapping("/estimate")
public class SearchVolumeController {

    SearchVolumeService searchVolumeService;

    @Autowired
    SearchVolumeController(SearchVolumeService searchVolumeService){
        this.searchVolumeService = searchVolumeService;
    }

    /**
     * this function is monitored by Hystrix circuit breaker, if the execution time takes more than 10 second, An error will be returned
     *
     * @param keyword is passed as string variable
     * @return response json of SearchVolume or ErrorDetail
     */
    @HystrixCommand(fallbackMethod = "timedOut", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "10000")
    })
    @GetMapping
    public ResponseEntity<?> getSearchVolume(@RequestParam(value = "keyword") String keyword) {
        return ResponseEntity.ok().body( searchVolumeService.getSearchVolume(keyword));
    }


    /**
     * this is fallback function which will be called by hystrix in case of timed out
     *
     * @param keyword input text
     * @param t error
     * @return response json of ErrorDetail
     */
    public ResponseEntity<?> timedOut(String keyword, Throwable t) {

        return ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT).body(
                new ErrorDetail.Builder()
                        .timeStamp(LocalDateTime.now())
                        .exception("TimeOut Exception")
                        .message("Request handling took more than 10 seconds")
                        .status(HttpStatus.REQUEST_TIMEOUT)
                        .build()
        );
    }

}



