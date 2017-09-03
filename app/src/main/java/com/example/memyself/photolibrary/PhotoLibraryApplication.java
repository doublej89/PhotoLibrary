package com.example.memyself.photolibrary;

import android.app.Application;

import io.realm.Realm;

/**
 * Created by MeMyself on 8/25/2017.
 */

public class PhotoLibraryApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
    }
}
