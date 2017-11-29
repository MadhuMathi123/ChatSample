package com.chatsample.user.mvp.addUser;

import android.content.Context;

import com.google.firebase.auth.FirebaseUser;

public class AddUserPresenter implements AddUserContract.presenter, AddUserContract.OnUserDatabaseListener {

    AddUserContract.view view;
    AddUserInteractor addUserInteractor;

    public AddUserPresenter(AddUserContract.view view) {
        this.view = view;
        addUserInteractor = new AddUserInteractor(this);
    }

    @Override
    public void addUser(Context context, FirebaseUser firebaseUser) {
        addUserInteractor.addUserToDatabase(context, firebaseUser);

    }

    @Override
    public void onSuccess(String message) {
        view.onAddUserSuccess(message);
    }

    @Override
    public void onFailure(String message) {
        view.onAddUserFailure(message);

    }
}
