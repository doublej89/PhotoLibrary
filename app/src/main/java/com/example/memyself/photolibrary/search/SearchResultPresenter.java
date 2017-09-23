package com.example.memyself.photolibrary.search;

import com.example.memyself.photolibrary.storage.Photo;

/**
 * Created by MeMyself on 9/23/2017.
 */

public interface SearchResultPresenter {
    void onCreate();
    void onDestroy();
    void doSearch(String tags);
    void save(Photo photo);
    void onEventMainThread(SearchEvent event);
}
