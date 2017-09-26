package com.example.memyself.photolibrary.search;

import com.example.memyself.photolibrary.flickr.Photo;
import com.example.memyself.photolibrary.storage.DbPhoto;

/**
 * Created by MeMyself on 9/23/2017.
 */

interface SearchResultModel {
    void doSearch(String tags);
    void save(Photo photo);
}
