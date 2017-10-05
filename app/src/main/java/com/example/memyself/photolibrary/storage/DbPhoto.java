package com.example.memyself.photolibrary.storage;

import android.text.TextUtils;

import com.example.memyself.photolibrary.flickr.Photo;

import io.realm.RealmObject;

/**
 * Created by MeMyself on 8/22/2017.
 */

public class DbPhoto extends RealmObject{
    private String id;

    private String url;

    public String title;
    public String server;
    public String secret;
    public int farm;

    public DbPhoto() {

    }

    public DbPhoto(Photo photo) {
        this.id = photo.getId();
        this.title = photo.getTitle();
        this.server = photo.getServer();
        this.secret = photo.getSecret();
        this.farm = photo.getFarm();
    }

    public String getUrl() {
        if (server != null || secret != null)
            return getFlickrUrl();
        return url;
    }

    public String getId() {
        return id;
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
