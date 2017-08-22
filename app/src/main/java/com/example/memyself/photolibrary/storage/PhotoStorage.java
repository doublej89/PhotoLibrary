package com.example.memyself.photolibrary.storage;

import android.content.Context;
import android.os.AsyncTask;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.memyself.photolibrary.R;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by MeMyself on 8/22/2017.
 */

public class PhotoStorage {
    static Cloudinary cloudinary;

    public PhotoStorage(Context context) {
        Map config = new HashMap();
        config.put("cloud_name", context.getString(R.string.CLOUD_NAME));
        config.put("api_key", context.getString(R.string.CLOUD_API_KEY));
        config.put("api_secret", context.getString(R.string.CLOUD_API_SECRET));

        cloudinary = new Cloudinary(config);
    }

    public String getImageUrl(String id) {
        return cloudinary.url().generate(id);
    }

    public static class CloudUploadAsyncTask extends AsyncTask<Void, Void, Void> {
        File file;
        PhotoStorageListener listener;
        String id;
        boolean success = false;

        public CloudUploadAsyncTask(File file, PhotoStorageListener listener, String id) {
            this.file = file;
            this.listener = listener;
            this.id = id;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            if (success) listener.onSuccess();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            Map params = ObjectUtils.asMap("public_id", id);
            try {
                cloudinary.uploader().upload(file, params);
                success = true;
            } catch (Exception e) {
                listener.onError(e.getMessage());
            }
            return null;
        }
    }

    public void upload(final File file, final String id, final PhotoStorageListener listener) {

        new CloudUploadAsyncTask(file, listener, id).execute();
    }
}
