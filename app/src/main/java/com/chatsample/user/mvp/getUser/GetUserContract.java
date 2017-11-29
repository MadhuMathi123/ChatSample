package com.chatsample.user.mvp.getUser;

import com.chatsample.user.models.User;

import java.util.List;

public interface GetUserContract {
    interface view {
        void success(List<User> users);

        void failure(String message);
    }

    interface presenter {
        void getAllUser();

        void getChatUsers();
    }

    interface interactor {
        void getAllUsersFromFirebase();

        void getChatUsersFromFirebase();
    }

    interface OnGetAllUsersListener {
        void onGetAllUsersSuccess(List<User> users);

        void onGetAllUsersFailure(String message);
    }

    interface OnGetChatUsersListener {
        void onGetChatUsersSuccess(List<User> users);

        void onGetChatUsersFailure(String message);
    }
}