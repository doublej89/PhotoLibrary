package com.example.memyself.photolibrary.search;

import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.memyself.photolibrary.R;
import com.example.memyself.photolibrary.storage.Photo;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.memyself.photolibrary.SearchActivity.TAGS_EXTRA;

public class SearchResultActivity extends AppCompatActivity implements SearchResultView{



    @BindView(R.id.displayImage)
    ImageView displayImageView;
    @BindView(R.id.titleTextView)
    TextView titleTextView;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    int selectedIndex = 0;
    public List<Photo> photoList;

    private SearchResultPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        ButterKnife.bind(this);

        String tags = getIntent().getStringExtra(TAGS_EXTRA);
        EventBus eventBus = new EventBus();
        presenter = new SearchResultPresenterImpl(eventBus, this, new SearchResultmodelimpl(eventBus));
        presenter.onCreate();
        presenter.doSearch(tags);
        if (photoList != null)
            loadPhoto(photoList.get(selectedIndex).getFlickrUrl());
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void setImageViewListener() {
        displayImageView.setOnTouchListener(new OnSwipeTouchListener(this) {

            @Override
            public void onSwipeDown() {
                showNextPhoto();
                Snackbar.make(titleTextView, "Passed", Snackbar.LENGTH_LONG).show();
            }

            @Override
            public void onSwipeLeft() {
                presenter.save(photoList.get(selectedIndex));
                showNextPhoto();
                Snackbar.make(titleTextView, "Saved", Snackbar.LENGTH_LONG).show();
            }

            @Override
            public void onSwipeUp() {
                showNextPhoto();
                Snackbar.make(titleTextView, "Passed", Snackbar.LENGTH_LONG).show();
            }

            @Override
            public void onSwipeRight() {
                presenter.save(photoList.get(selectedIndex));
                showNextPhoto();
                Snackbar.make(titleTextView, "Saved", Snackbar.LENGTH_LONG).show();
            }

        });
    }

    @Override
    public void loadPhoto(String url) {
        progressBar.setVisibility(View.VISIBLE);
        titleTextView.setText("");

        Glide.with(getApplicationContext())
                .load(url)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        progressBar.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        progressBar.setVisibility(View.GONE);
                        titleTextView.setText("Title: " + photoList.get(selectedIndex).title);
                        return false;
                    }
                })
                .into(displayImageView);
    }

    @Override
    public void showNextPhoto() {
        if (selectedIndex < photoList.size() - 1) {
            selectedIndex = selectedIndex + 1;

            loadPhoto(photoList.get(selectedIndex).getFlickrUrl());
        } else {
            Toast.makeText(getBaseContext(), "No more photos to show!", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void setList(List<Photo> photos) {
        this.photoList = photos;
    }

    @Override
    public void showError() {
        Toast.makeText(getBaseContext(), "There was an error in the Flickr response", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showServerError() {
        Toast.makeText(getBaseContext(), "There was an error when calling the Flickr search endpoint.", Toast.LENGTH_SHORT).show();
    }
}
