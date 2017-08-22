package com.example.memyself.photolibrary.storage;

/**
 * Created by MeMyself on 8/22/2017.
 */

public interface PhotoStorageListener {
    void onSuccess();
    void onError(String error);
}
