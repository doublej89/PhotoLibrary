package com.example.memyself.photolibrary.search;

import com.example.memyself.photolibrary.storage.Photo;

import java.util.List;

/**
 * Created by MeMyself on 9/22/2017.
 */

public interface SearchResultView {
    void setImageViewListener();
    void loadPhoto(String url);
    void showNextPhoto();
    void hideProgress();
    void setList(List<Photo> photos);
    void showError();
    void showServerError();
}
