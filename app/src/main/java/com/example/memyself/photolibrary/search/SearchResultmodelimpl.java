package com.example.memyself.photolibrary.search;

import com.example.memyself.photolibrary.flickr.FlickrClient;
import com.example.memyself.photolibrary.flickr.FlickrService;
import com.example.memyself.photolibrary.flickr.Photo;
import com.example.memyself.photolibrary.flickr.Photos;
import com.example.memyself.photolibrary.flickr.PhotosResponse;
import com.example.memyself.photolibrary.storage.DbPhoto;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



/**
 * Created by MeMyself on 9/23/2017.
 */

public class SearchResultmodelimpl implements SearchResultModel {
    private EventBus eventBus;
    private static final int PAGE_SIZE = 15;

    public SearchResultmodelimpl(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    @Override
    public void doSearch(String tags) {
        FlickrClient flickrClient = new FlickrClient();

        FlickrService flickrService = flickrClient.getFlickrService();

        Call<PhotosResponse> call = flickrService.search(tags, PAGE_SIZE);
        call.enqueue(new Callback<PhotosResponse>() {

            @Override
            public void onResponse(Call<PhotosResponse> call, Response<PhotosResponse> response) {
                if (response.isSuccessful()) {
                    PhotosResponse photosResponse = response.body();
                    if (photosResponse != null) {
                        Photos photos = photosResponse.getPhotos();

                        post(SearchEvent.READ_EVENT, photos.getPhoto());
                    }
                } else {
                    post(SearchEvent.ERROR_EVENT, null);
                }
            }

            @Override
            public void onFailure(Call<PhotosResponse> call, Throwable t) {
                post(SearchEvent.SERVER_ERROR_EVENT, null);
            }
        });
    }

    @Override
    public void save(Photo photo) {
        DbPhoto dbPhoto = new DbPhoto(photo);
        Realm realm = Realm.getDefaultInstance();
        DbPhoto realmDbPhoto = realm.copyToRealm(dbPhoto);
        realm.commitTransaction();
    }

    private void post(int type, List<Photo> photoList) {
        SearchEvent event = new SearchEvent();
        event.setType(type);
        event.setPhotoList(photoList);
        eventBus.post(event);
    }
}
