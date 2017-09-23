package com.example.memyself.photolibrary.storage;

import android.text.TextUtils;

import io.realm.RealmObject;

/**
 * Created by MeMyself on 8/22/2017.
 */

public class Photo extends RealmObject{
    private String id;

    private String url;

    public String title;
    public String server;
    public String secret;
    public int farm;

    public String getId() {
        return id;
    }

    public String getUrl() {
        if (!TextUtils.isEmpty(server) && !TextUtils.isEmpty(secret))
            getFlickrUrl();
        return url;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFlickrUrl() {
        return "https://farm" + farm + ".staticflickr.com/" + server + "/" + id + "_" + secret + "_b.jpg";
    }
}
