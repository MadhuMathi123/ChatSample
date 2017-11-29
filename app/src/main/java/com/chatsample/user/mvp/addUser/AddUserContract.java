package com.chatsample.user.mvp.addUser;

import android.content.Context;

import com.google.firebase.auth.FirebaseUser;

/**
 * Created by Ocs pl-79(17.2.2016) on 11/27/2017.
 */

public interface AddUserContract {
    interface view {
        void onAddUserSuccess(String message);

        void onAddUserFailure(String message);
    }

    interface presenter {
        void addUser(Context context, FirebaseUser firebaseUser);
    }

    interface Interactor {
        void addUserToDatabase(Context context, FirebaseUser firebaseUser);
    }

    interface OnUserDatabaseListener {
        void onSuccess(String message);

        void onFailure(String message);
    }
}
