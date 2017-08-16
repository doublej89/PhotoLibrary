package com.example.memyself.photolibrary.login;

/**
 * Created by MeMyself on 8/16/2017.
 */

public interface LoginModel {
    void signUp(final String email, final String password);
    void signIn(String email, String password);
}
