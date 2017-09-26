package com.example.memyself.photolibrary.search;

import com.example.memyself.photolibrary.flickr.Photo;
import com.example.memyself.photolibrary.storage.DbPhoto;

import java.util.List;

/**
 * Created by MeMyself on 9/23/2017.
 */

public class SearchEvent {
    private int type;
    private List<Photo> photoList;

    public final static int SERVER_ERROR_EVENT = 1;
    public final static int READ_EVENT = 2;
    public final static int ERROR_EVENT = 3;

    public List<Photo> getPhotoList() {
        return photoList;
    }

    public void setPhotoList(List<Photo> dbPhotoList) {
        this.photoList = dbPhotoList;
    }


    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
