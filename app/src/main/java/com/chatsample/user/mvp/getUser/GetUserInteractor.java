package com.chatsample.user.mvp.getUser;


import android.text.TextUtils;

import com.chatsample.utils.Constants;
import com.chatsample.user.models.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GetUserInteractor implements GetUserContract.interactor {
    private static final String TAG = GetUserContract.class.getSimpleName();

    GetUserContract.OnGetAllUsersListener onGetAllUsersListener;

    public GetUserInteractor(GetUserContract.OnGetAllUsersListener onGetAllUsersListener) {
        this.onGetAllUsersListener = onGetAllUsersListener;
    }

    @Override
    public void getAllUsersFromFirebase() {
        FirebaseDatabase.getInstance().getReference().child(Constants.ARG_USERS).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterator<DataSnapshot> dataSnapshotIterator = dataSnapshot.getChildren().iterator();
                List<User> users = new ArrayList<>();
                while (dataSnapshotIterator.hasNext()) {
                    DataSnapshot dataSnapshott = dataSnapshotIterator.next();
                    User user = dataSnapshott.getValue(User.class);
//                    if (!TextUtils.equals(user.uid, FirebaseAuth.getInstance().getCurrentUser().getUid())) {
                        users.add(user);
//                    }
                }
                onGetAllUsersListener.onGetAllUsersSuccess(users);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                onGetAllUsersListener.onGetAllUsersFailure(databaseError.getMessage());

            }
        });

    }

    @Override
    public void getChatUsersFromFirebase() {

    }
}
