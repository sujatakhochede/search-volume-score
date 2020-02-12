/*
 *   Copyright (c) 2020 The Sellics Assignment.
 *   All Rights Reserved.
 *
 */

package com.sellics.search.service;

/**
 * Service endpoint for the consumption of Amazone auto complete API
 *
 * @author Sujata Khochede
 * @since 12-FEB-2020
 */
public interface AmazoneApiService {
     String callAutoCompleteApi(String keyword);
}
