/*
 *   Copyright (c) 2020 The Sellics Assignment.
 *   All Rights Reserved.
 *
 */

package com.sellics.search.service.impl;

import com.sellics.search.mapper.AmazonResponseMapper;
import com.sellics.search.mapper.SearchVolumeRequestMapper;
import com.sellics.search.model.SearchVolume;
import com.sellics.search.service.AmazoneApiService;
import com.sellics.search.service.SearchVolumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.function.Predicate;

/**
 * Service implementation to get most estimated search volume.
 *
 * @author Sujata Khochede
 * @since 12-FEB-2020
 */
@Service
public class SearchVolumeServiceImpl implements SearchVolumeService {

    AmazoneApiService amazoneApiService;
    AmazonResponseMapper amazonResponseMapper;
    SearchVolumeRequestMapper requestMapper;

    @Autowired
    SearchVolumeServiceImpl(AmazoneApiService amazoneApiService, AmazonResponseMapper amazonResponseMapper, SearchVolumeRequestMapper requestMapper) {
        this.amazoneApiService = amazoneApiService;
        this.amazonResponseMapper = amazonResponseMapper;
        this.requestMapper = requestMapper;
    }


    /**
     * Business logic to get estimated search volume result.
     *
     * @param  keyword text entered in search
     * @return  SearchVolume containing score
     */
    public SearchVolume getSearchVolume(String keyword) {
        //get top suggestions from amazone API
        List<String> suggestions = amazonResponseMapper.transformApiResponse
                (amazoneApiService.callAutoCompleteApi(keyword));

        //compute score
        int score = computeSearchVolumeScore(requestMapper.cleanUpText(keyword), suggestions);

        //return search volume result
        return new SearchVolume(keyword, score);
    }

    /**
     * Computes search volume score.
     *
     * @param  keyword keyword
     * @param  suggestions candidate list from Amazone auto complete API
     * @return score ranging from 0-100
     */
    private int computeSearchVolumeScore(String keyword, List<String> suggestions) {
        //Look for exact prefix match for whole word, thats why space at the end
        Predicate<String> isPrefixExactMatch = s -> s.equalsIgnoreCase(keyword) || s.startsWith(keyword + " ");
        long numberOfPrefixMatches = suggestions
                .stream()
                .filter(suggestion -> isPrefixExactMatch.test(suggestion))
                .count();

        //avoiding score going beyond 100 in case amazone api returns >10 suggestions, magic number 10 can be moved to config
        if (numberOfPrefixMatches > 10) {
            numberOfPrefixMatches = 10;
        }

        return (int) numberOfPrefixMatches * 10;
    }

}
