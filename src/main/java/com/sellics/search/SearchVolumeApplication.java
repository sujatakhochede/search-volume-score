/*
 *   Copyright (c) 2020 The Sellics Assignment.
 *   All Rights Reserved.
 *
 */

package com.sellics.search;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;


/**
 * Main class for search application
 *
 * @author Sujata Khochede
 * @since 12-FEB-2020
 */
@SpringBootApplication
@Configuration
@EnableHystrixDashboard
@EnableCircuitBreaker
public class SearchVolumeApplication {

    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

    /**
     * Launches the application
     * @param args arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(SearchVolumeApplication.class, args);
    }


}
