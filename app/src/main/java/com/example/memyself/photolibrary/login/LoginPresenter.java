package com.example.memyself.photolibrary.login;

/**
 * Created by MeMyself on 8/16/2017.
 */

public interface LoginPresenter {
    void onCreate();
    void onDestroy();
    void onEventMainThread(LoginEvent event);
    void login(String email, String password);
    void signup(String email, String password);
}
