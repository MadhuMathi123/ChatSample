package com.chatsample.register.mvp;

import android.app.Activity;

import com.google.firebase.auth.FirebaseUser;


public class RegisterPresenter implements RegisterContract.Presenter, RegisterContract.onRegistrationListener {
    private RegisterContract.View mRegisterView;
    private RegisterInteractor mRegisterInteractor;

    public RegisterPresenter(RegisterContract.View registerView) {
        this.mRegisterView = registerView;
        mRegisterInteractor = new RegisterInteractor(this);
    }

    @Override
    public void onRegister(Activity activity, String email, String password) {
        mRegisterInteractor.onPerformFirebaseRegistration(activity, email, password);
    }

    @Override
    public void onRegisterSuccessResponse(FirebaseUser firebaseUser) {
        mRegisterView.onRegistrationSuccess(firebaseUser);
    }

    @Override
    public void onRegisterFailureResponse(String message) {
        mRegisterView.onRegistrationFailure(message);

    }
}
