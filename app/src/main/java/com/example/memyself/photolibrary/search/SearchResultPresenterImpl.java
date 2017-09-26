package com.example.memyself.photolibrary.search;

import com.example.memyself.photolibrary.flickr.Photo;
import com.example.memyself.photolibrary.storage.DbPhoto;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * Created by MeMyself on 9/23/2017.
 */

public class SearchResultPresenterImpl implements SearchResultPresenter {
    private EventBus eventBus;
    private SearchResultView view;
    private SearchResultModel model;

    public SearchResultPresenterImpl(EventBus eventBus, SearchResultView view, SearchResultModel model) {
        this.eventBus = eventBus;
        this.view = view;
        this.model = model;
    }

    @Override
    public void onCreate() {
        eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        this.view = null;
        eventBus.unregister(this);
    }

    @Override
    public void doSearch(String tags) {
        model.doSearch(tags);
    }

    @Override
    public void save(Photo photo) {
        model.save(photo);
    }

    @Override
    @Subscribe
    public void onEventMainThread(SearchEvent event) {
        if (view != null) {
            view.hideProgress();
            if (event.getType() == SearchEvent.READ_EVENT)
                view.setList(event.getPhotoList());
            else if (event.getType() == SearchEvent.ERROR_EVENT)
                view.showError();
        }
    }
}
