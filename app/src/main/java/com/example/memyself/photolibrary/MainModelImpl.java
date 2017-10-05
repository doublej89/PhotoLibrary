package com.example.memyself.photolibrary;

import com.example.memyself.photolibrary.storage.DbPhoto;
import com.example.memyself.photolibrary.storage.PhotoStorage;
import com.example.memyself.photolibrary.storage.PhotoStorageListener;
import com.google.firebase.auth.FirebaseAuth;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.security.SecureRandom;
import java.util.Locale;

import io.realm.Realm;

/**
 * Created by MeMyself on 8/22/2017.
 */

public class MainModelImpl implements MainModel {
    private static final String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private static final String lower = upper.toLowerCase(Locale.ROOT);

    private static final String digits = "0123456789";

    private static final char[] alphanum = (upper + lower + digits).toCharArray();

    public String nextString() {
        SecureRandom random = new SecureRandom();
        char[] buf = new char[8];
        for (int idx = 0; idx < buf.length; ++idx)
            buf[idx] = alphanum[random.nextInt(alphanum.length)];
        return new String(buf);
    }

    private EventBus eventBus;
    private PhotoStorage storage;

    public MainModelImpl(EventBus eventBus, PhotoStorage storage) {
        this.eventBus = eventBus;
        this.storage = storage;
    }

    @Override
    public void logout() {
        FirebaseAuth.getInstance().signOut();
    }

    @Override
    public void uploadPhoto(String path) {
        String id = nextString();
        final DbPhoto dbPhoto = new DbPhoto();
        dbPhoto.setId(id);

        post(MainEvent.UPLOAD_INIT);
        storage.upload(new File(path), dbPhoto.getId(), new PhotoStorageListener() {
            @Override
            public void onSuccess() {
                dbPhoto.setUrl(storage.getImageUrl(dbPhoto.getId()));
                Realm realm = Realm.getDefaultInstance();
                realm.beginTransaction();
                DbPhoto realmDbPhoto = realm.copyToRealm(dbPhoto);
                realm.commitTransaction();
            }

            @Override
            public void onError(String error) {
                post(MainEvent.UPLOAD_ERROR);
            }
        });
    }

    private void post(int type){
        post(type, null);
    }

    private void post(int type, String error){
        MainEvent event = new MainEvent();
        event.setType(type);
        event.setError(error);
        eventBus.post(event);
    }
}
