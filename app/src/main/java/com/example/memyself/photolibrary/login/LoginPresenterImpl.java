package com.example.memyself.photolibrary.login;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * Created by MeMyself on 8/16/2017.
 */

public class LoginPresenterImpl implements LoginPresenter{
    private LoginModel model;
    private LoginView view;
    private EventBus eventBus;

    public LoginPresenterImpl(LoginModel model, LoginView view, EventBus eventBus) {
        this.model = model;
        this.view = view;
        this.eventBus = eventBus;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    @Subscribe
    public void onEventMainThread(LoginEvent event) {

    }

    @Override
    public void login(String email, String password) {

    }

    @Override
    public void signup(String email, String password) {

    }
}
