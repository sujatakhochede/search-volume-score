/*
 *   Copyright (c) 2020 The Sellics Assignment.
 *   All Rights Reserved.
 *
 */

package com.sellics.search.service;

import com.sellics.search.model.SearchVolume;

/**
 * Service endpoint to get most estimated search volume.
 *
 * @author Sujata Khochede
 * @since 12-FEB-2020
 */
public interface SearchVolumeService {
    SearchVolume getSearchVolume(String keyword);
}
