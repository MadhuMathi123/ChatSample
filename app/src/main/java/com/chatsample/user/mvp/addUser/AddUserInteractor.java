package com.chatsample.user.mvp.addUser;


import android.content.Context;
import android.support.annotation.NonNull;

import com.chatsample.utils.Constants;
import com.chatsample.utils.PreferenceManager;
import com.chatsample.R;
import com.chatsample.user.models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddUserInteractor implements  AddUserContract.Interactor {

    AddUserContract.OnUserDatabaseListener onUserDatabaseListener;

    public AddUserInteractor(AddUserContract.OnUserDatabaseListener onUserDatabaseListener) {
        this.onUserDatabaseListener = onUserDatabaseListener;
    }

    @Override
    public void addUserToDatabase(final Context context, FirebaseUser firebaseUser) {
        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference();
        User user  = new User();
        user.setEmail(firebaseUser.getEmail());
        user.setUid(firebaseUser.getUid());
        user.setFirebaseToken( new PreferenceManager(context).getString(Constants.ARG_FIREBASE_TOKEN));
        databaseReference.child(Constants.ARG_USERS)
                .child(firebaseUser.getUid())
                .setValue(user)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            onUserDatabaseListener.onSuccess(context.getString(R.string.user_successfully_added));
                        } else {
                            onUserDatabaseListener.onFailure(context.getString(R.string.user_unable_to_add));
                        }
                    }
                });
    }

}
