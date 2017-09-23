package com.example.memyself.photolibrary.search;

import com.example.memyself.photolibrary.storage.Photo;

/**
 * Created by MeMyself on 9/23/2017.
 */

interface SearchResultModel {
    void doSearch(String tags);
    void save(Photo photo);
}
