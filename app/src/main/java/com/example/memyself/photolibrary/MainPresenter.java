package com.example.memyself.photolibrary;

import android.location.Location;

/**
 * Created by MeMyself on 8/21/2017.
 */

public interface MainPresenter {
    void onCreate();
    void onDestroy();

    void logout();
    void uploadPhoto(String path);
    void onEventMainThread(MainEvent event);
}
