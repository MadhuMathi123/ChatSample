package com.chatsample.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.chatsample.R;
import com.chatsample.login.mvp.LoginContract;
import com.chatsample.login.mvp.LoginPresenter;
import com.chatsample.register.RegisterActivity;
import com.chatsample.user.UserActivity;

/**
 * A placeholder fragment containing a simple view.
 */
public class LoginFragment extends Fragment implements View.OnClickListener, LoginContract.View {
    EditText etLoginEmailId;
    EditText etLoginPswd;
    Button btnLogin;
    Button btnRegister;
    private LoginPresenter mLoginPresenter;
    private ProgressDialog mProgressDialog;

    public LoginFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        etLoginEmailId = view.findViewById(R.id.etLoginEmailId);
        etLoginPswd = view.findViewById(R.id.etLoginPswd);
        btnLogin = view.findViewById(R.id.btnLogin);
        btnRegister = view.findViewById(R.id.btnRegister);

        init();
    }

    private void init() {
        mLoginPresenter = new LoginPresenter(this);

        mProgressDialog = new ProgressDialog(getActivity());
        mProgressDialog.setTitle(getString(R.string.loading));
        mProgressDialog.setMessage(getString(R.string.please_wait));
        mProgressDialog.setIndeterminate(true);

        btnLogin.setOnClickListener(this);
        btnRegister.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        int viewId = view.getId();

        switch (viewId) {
            case R.id.btnLogin:
                onLogin();
                break;
            case R.id.btnRegister:
                onRegister();
                break;
        }
    }

    private void onLogin() {
        String emailId = etLoginEmailId.getText().toString();
        String password = etLoginPswd.getText().toString();

        mLoginPresenter.login(getActivity(), emailId, password);
        mProgressDialog.show();
    }

    private void onRegister() {
        RegisterActivity.startActivity(getActivity());
    }

    @Override
    public void onLoginSuccess(String message) {
        mProgressDialog.dismiss();
        Toast.makeText(getActivity(), "Logged in successfully", Toast.LENGTH_SHORT).show();
        UserActivity.startActivity(getActivity(),
                Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
    }

    @Override
    public void onLoginFailure(String message) {
        mProgressDialog.dismiss();
        Toast.makeText(getActivity(), "Error: " + message, Toast.LENGTH_SHORT).show();
    }
}
