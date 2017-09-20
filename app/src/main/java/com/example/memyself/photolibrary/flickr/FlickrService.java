package com.example.memyself.photolibrary.flickr;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by MeMyself on 9/21/2017.
 */

public interface FlickrService {
    @GET("/services/rest/?method=flickr.photos.search")
    Call<PhotosResponse> search(@Query("tags") String tags, @Query("per_page") int perPage);
}
