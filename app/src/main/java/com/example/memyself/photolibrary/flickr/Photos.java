package com.example.memyself.photolibrary.flickr;

import com.example.memyself.photolibrary.storage.Photo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MeMyself on 9/21/2017.
 */

public class Photos {
    int page;
    int pages;
    int perPage;
    long total;
    public List<Photo> photoList = new ArrayList<>();
}
