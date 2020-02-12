/*
 *   Copyright (c) 2020 The Sellics Assignment.
 *   All Rights Reserved.
 *
 */

package com.sellics.search.mapper;

import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class SearchVolumeRequestMapper {

    /**
     * Cleans up input text
     *
     * @param inputText free text entered
     * @return Cleaned string
     */
    public String cleanUpText(String inputText){
        return inputText.trim()
                .replaceAll("\\s+"," ")
                .toLowerCase(Locale.ENGLISH);
    }
}
