package com.chatsample.register.mvp;

import android.app.Activity;

import com.google.firebase.auth.FirebaseUser;

/**
 * Created by Ocs pl-79(17.2.2016) on 11/24/2017.
 */

public interface RegisterContract {
    interface View {
        void onRegistrationSuccess(FirebaseUser firebaseUser);

        void onRegistrationFailure(String message);
    }

    interface Presenter {
        void onRegister(Activity activity, String email, String password);
    }

    interface Interacter {
        void onPerformFirebaseRegistration(Activity activity, String email, String password);
    }

    interface onRegistrationListener {
        void onRegisterSuccessResponse(FirebaseUser firebaseUser);

        void onRegisterFailureResponse(String message);
    }
}
