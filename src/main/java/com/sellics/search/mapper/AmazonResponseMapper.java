/*
 *   Copyright (c) 2020 The Sellics Assignment.
 *   All Rights Reserved.
 *
 */

package com.sellics.search.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class to transform Amazone API response
 *
 * @author Sujata Khochede
 * @since 12-FEB-2020
 */
@Component
public class AmazonResponseMapper {

    Logger logger = LoggerFactory.getLogger(AmazonResponseMapper.class);
    private ObjectMapper mapper;

    @Autowired
    public AmazonResponseMapper(ObjectMapper mapper){
        this.mapper = mapper;
    }

    /**
     * transform Amazone API response to estimate search volume
     *
     * @author Sujata Khochede
     * @param response auto complete API
     * @return List of suggestions
     *
     */
    public List<String> transformApiResponse(String response)  {

        List<String> suggestions =  new ArrayList<>();
        try {
            suggestions = (List<String>) mapper.readValue(response, List.class).get(1);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        return suggestions;
    }
}
