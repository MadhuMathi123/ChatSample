package com.chatsample.user.mvp.getUser;


import com.chatsample.user.models.User;

import java.util.List;

public class GetUserPresenter implements GetUserContract.presenter, GetUserContract.OnGetAllUsersListener {
    GetUserContract.view view;
    GetUserInteractor interactor;

    public GetUserPresenter(GetUserContract.view view) {
        this.view = view;
        interactor = new GetUserInteractor(this);
    }

    @Override
    public void getAllUser() {

        interactor.getAllUsersFromFirebase();
    }

    @Override
    public void getChatUsers() {
        interactor.getChatUsersFromFirebase();
    }

    @Override
    public void onGetAllUsersSuccess(List<User> users) {

        view.success(users);
    }

    @Override
    public void onGetAllUsersFailure(String message) {
        view.failure(message);
    }
}
