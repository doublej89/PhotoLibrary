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
        eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        view = null;
        eventBus.unregister(this);
    }

    @Override
    @Subscribe
    public void onEventMainThread(LoginEvent event) {
        switch (event.getEventType()) {
            case LoginEvent.onSignInError:
                if (view != null) {
                    view.hideProgress();
                    view.enableInputs();
                    view.loginError(event.getErrorMesage());
                }
                break;
            case LoginEvent.onSignInSuccess:
                if (view != null) {
                    view.setUserEmail(event.getLoggedUserEmail());
                    view.navigateToMainScreen();
                }
                break;
            case LoginEvent.onSignUpError:
                if (view != null) {
                    view.hideProgress();
                    view.enableInputs();
                    view.newUserError(event.getErrorMesage());
                }
                break;
            case LoginEvent.onSignUpSuccess:
                if (view != null) {
                    view.newUserSuccess();
                }
                break;
            case LoginEvent.onFailedToRecoverSession:
                if (view != null) {
                    view.hideProgress();
                    view.enableInputs();
                }
                break;
        }
    }

    @Override
    public void login(String email, String password) {
        model.signIn(email, password);
    }

    @Override
    public void signup(String email, String password) {
        model.signUp(email, password);
    }
}
