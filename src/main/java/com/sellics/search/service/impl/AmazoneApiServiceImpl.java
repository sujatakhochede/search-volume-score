/*
 *   Copyright (c) 2020 The Sellics Assignment.
 *   All Rights Reserved.
 *
 */

package com.sellics.search.service.impl;

import com.sellics.search.executor.RestServiceExecutor;
import com.sellics.search.service.AmazoneApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Service implementation for the consumption of Amazone auto complete API
 *
 * @author Sujata Khochede
 * @since 12-FEB-2020
 */
@Service
public class AmazoneApiServiceImpl implements AmazoneApiService {

    private final String SEARCH_COMPLETE_SERVICE_URL = "https://completion.amazon.com/search/complete?search-alias=aps&mkt=1&q={q}";

    RestServiceExecutor restServiceExecutor;

    @Autowired
    public AmazoneApiServiceImpl(RestServiceExecutor restServiceExecutor) {
        this.restServiceExecutor = restServiceExecutor;
    }

    public String callAutoCompleteApi(String keyword) {
        Map<String, String> params = new HashMap<>();
        params.put("q", keyword.replace(" ", "+"));
        return restServiceExecutor.httpGet(SEARCH_COMPLETE_SERVICE_URL, params);
    }
}
