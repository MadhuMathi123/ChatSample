package com.chatsample.register.mvp;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class RegisterInteractor implements RegisterContract.Interacter {

    private static final String TAG = RegisterInteractor.class.getSimpleName();
    RegisterContract.onRegistrationListener onRegistrationListener;

    public RegisterInteractor(RegisterContract.onRegistrationListener onRegistrationListener) {
        this.onRegistrationListener = onRegistrationListener;
    }

    @Override
    public void onPerformFirebaseRegistration(Activity activity, String email, String password) {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password).addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (!task.isSuccessful()) {
                    onRegistrationListener.onRegisterFailureResponse(task.getException().getMessage());//on failure response
                } else {
                    onRegistrationListener.onRegisterSuccessResponse(task.getResult().getUser());//on success response
                }

            }
        });
    }
}
