package com.example.memyself.photolibrary.storage;

import io.realm.RealmObject;

/**
 * Created by MeMyself on 8/22/2017.
 */

public class Photo extends RealmObject{
    private String id;
    private String url;

    public String getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
