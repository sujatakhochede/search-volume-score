/*
 *   Copyright (c) 2020 The Sellics Assignment.
 *   All Rights Reserved.
 *
 */

package com.sellics.search.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Search volume estimation response model
 *
 * @author Sujata Khochede
 * @since 12-FEB-2020
 */
public class SearchVolume {

    /**
     * Keyword received from client
     * TODO - typo in assignment, however correcting it here
     */
    @JsonProperty(value = "keyword")
    private String keyword;


    /**
     * The score should be in the range [0 → 100] and represent the estimated search-volume (how often Amazon customers
     * search for that exact keyword). A score of 0 means that the keyword is practically never searched for,
     * 100 means that this is one of the hottest keywords in all of amazon.com right now
     */
    @JsonProperty(value = "score")
    private int score;

    public SearchVolume(String keyword, int score){
        this.keyword = keyword;
        this.score = score;
    }

    @Override
    public String toString() {
        return "SearchVolume{" +
                "keyword='" + keyword + '\'' +
                ", score=" + score +
                '}';
    }
}
