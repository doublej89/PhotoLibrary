package com.example.memyself.photolibrary.login;

/**
 * Created by MeMyself on 8/16/2017.
 */

public interface LoginView {
    void enableInputs();
    void disableInputs();
    void showProgress();
    void hideProgress();
    void newUserSuccess();
    void navigateToMainScreen();
    void setUserEmail(String email);
    void loginError(String error);
    void newUserError(String error);
}
