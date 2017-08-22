package com.example.memyself.photolibrary.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.example.memyself.photolibrary.MainActivity;
import com.example.memyself.photolibrary.R;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements LoginView{
    @BindView(R.id.btnSignIn)
    Button btnSignIn;
    @BindView(R.id.btnSignUp)
    Button btnSignUp;
    @BindView(R.id.editTxtEmail)
    EditText inputEmail;
    @BindView(R.id.editTxtPassword)
    EditText inputPassword;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.layoutMainContainer)
    FrameLayout container;

    LoginPresenter presenter;
    SharedPreferences prefs;
    private final static String EMAIL_KEY = "email";
    public final static String PREF_NAME = "userPref";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        prefs = getSharedPreferences(PREF_NAME, MODE_PRIVATE);

        EventBus eventBus = new EventBus();
        presenter = new LoginPresenterImpl(new LoginModelImpl(eventBus), this, eventBus);
        presenter.onCreate();
        presenter.login(null, null);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    public void enableInputs() {
        setInputs(true);
    }

    @Override
    public void disableInputs() {
        setInputs(false);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    @OnClick(R.id.btnSignUp)
    public void handlesSignUp() {
        presenter.signup(inputEmail.getText().toString(), inputPassword.getText().toString());
    }

    @Override
    @OnClick()
    public void handlesSignIn() {
        presenter.login(inputEmail.getText().toString(), inputPassword.getText().toString());
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void newUserSuccess() {
        Snackbar.make(container, R.string.login_notice_message_useradded, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void navigateToMainScreen() {
        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    public void setUserEmail(String email) {
        if (email != null) {
            prefs.edit().putString(EMAIL_KEY, email).apply();
        }
    }

    @Override
    public void loginError(String error) {
        inputPassword.setText("");
        String msgError = String.format(getString(R.string.login_error_message_signin), error);
        inputPassword.setError(msgError);
    }

    @Override
    public void newUserError(String error) {
        inputPassword.setText("");
        String msgError = String.format(getString(R.string.login_error_message_signup), error);
        inputPassword.setError(msgError);
    }

    private void setInputs(boolean enabled) {
        btnSignIn.setEnabled(enabled);
        btnSignUp.setEnabled(enabled);
        inputEmail.setEnabled(enabled);
        inputPassword.setEnabled(enabled);
    }
}
