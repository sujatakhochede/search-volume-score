/*
 *   Copyright (c) 2020 The Sellics Assignment.
 *   All Rights Reserved.
 *
 */

package com.sellics.search.executor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * Rest service executor class to make http calls to external services
 *
 * @author Sujata Khochede
 * @since 12-FEB-2020
 */
@Component
public class RestServiceExecutor {

    private RestTemplate restTemplate;

    @Autowired
    public RestServiceExecutor(RestTemplate restTemplate){
        this.restTemplate=restTemplate;
    }


    /**
     * Makes http GET call
     *
     * @author Sujata Khochede
     * @param url service url
     * @param parameters request parameters
     * @return json response
     */
    public String httpGet(String url, Map parameters){
        return restTemplate.getForObject(url, String.class, parameters);
    }

}
