package com.example.memyself.photolibrary;

/**
 * Created by MeMyself on 8/21/2017.
 */

public interface MainView {
    void onUploadInit();
    void onUploadComplete();
    void onUploadError(String error);
}
