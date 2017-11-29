package com.chatsample.chat.mvp;

import android.content.Context;
import android.util.Log;

import com.chatsample.chat.models.Chat;
import com.chatsample.fcm.FcmNotificationBuilder;
import com.chatsample.utils.Constants;
import com.chatsample.utils.PreferenceManager;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by Ocs pl-79(17.2.2016) on 11/27/2017.
 */

public class ChatInteractor implements ChatContract.interactor {
    private static final String TAG = ChatInteractor.class.getSimpleName();

    private ChatContract.onSendMsgListener mOnSendMessageListener;
    private ChatContract.onGetMsgListener mOnGetMessagesListener;

    public ChatInteractor(ChatContract.onSendMsgListener mOnSendMessageListener) {
        this.mOnSendMessageListener = mOnSendMessageListener;
    }

    public ChatInteractor(ChatContract.onGetMsgListener mOnGetMessagesListener) {
        this.mOnGetMessagesListener = mOnGetMessagesListener;
    }

    public ChatInteractor(ChatContract.onSendMsgListener mOnSendMessageListener, ChatContract.onGetMsgListener mOnGetMessagesListener) {
        this.mOnSendMessageListener = mOnSendMessageListener;
        this.mOnGetMessagesListener = mOnGetMessagesListener;
    }

    @Override
    public void sendMessageToFirebaseUser(final Context context, final Chat chat, final String receiverFirebaseToken) {

        final String chat_type_1 = chat.senderUid + "_" + chat.receiverUid;
        final String chat_type_2 = chat.receiverUid + "_" + chat.senderUid;

        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child(Constants.ARG_CHAT_ROOMS).getRef().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild(chat_type_1)) {
                    Log.e(TAG, "sendMessageToFirebaseUser: " + chat_type_1 + " exists");
                    databaseReference.child(Constants.ARG_CHAT_ROOMS).child(chat_type_1).child(String.valueOf(chat.timestamp)).setValue(chat);
                } else if (dataSnapshot.hasChild(chat_type_2)) {
                    Log.e(TAG, "sendMessageToFirebaseUser: " + chat_type_2 + " exists");
                    databaseReference.child(Constants.ARG_CHAT_ROOMS).child(chat_type_2).child(String.valueOf(chat.timestamp)).setValue(chat);

                } else {
                    Log.e(TAG, "sendMessageToFirebaseUser:  success");
                    databaseReference.child(Constants.ARG_CHAT_ROOMS).child(chat_type_1).child(String.valueOf(chat.timestamp)).setValue(chat);
                    getMessageFromFirebaseUser(chat.senderUid, chat.receiverUid);
                }

                // send push notification to the receiver
                sendPushNotificationToReceiver(chat.sender,
                        chat.message,
                        chat.senderUid,
                        new PreferenceManager(context).getString(Constants.ARG_FIREBASE_TOKEN),
                        receiverFirebaseToken);
                mOnSendMessageListener.onSendMsgSuccess();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void sendPushNotificationToReceiver(String username,
                                                String message,
                                                String uid,
                                                String firebaseToken,
                                                String receiverFirebaseToken) {
        FcmNotificationBuilder.initialize()
                .title(username)
                .message(message)
                .username(username)
                .uid(uid)
                .firebaseToken(firebaseToken)
                .receiverFirebaseToken(receiverFirebaseToken)
                .send();
    }

    @Override
    public void getMessageFromFirebaseUser(String senderUid, String receiverUid) {
        final String chat_type_1 = senderUid + "_" + receiverUid;
        final String chat_type_2 = receiverUid + "_" + senderUid;

        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

        databaseReference.child(Constants.ARG_CHAT_ROOMS).getRef().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild(chat_type_1)) {
                    Log.e(TAG, "getMessageFromFirebaseUser: " + chat_type_1 + " exists");
                    FirebaseDatabase.getInstance()
                            .getReference()
                            .child(Constants.ARG_CHAT_ROOMS)
                            .child(chat_type_1).addChildEventListener(new ChildEventListener() {
                        @Override
                        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                            Chat chat = dataSnapshot.getValue(Chat.class);
                            mOnGetMessagesListener.onGetMsgSuccess(chat);
                        }

                        @Override
                        public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                        }

                        @Override
                        public void onChildRemoved(DataSnapshot dataSnapshot) {

                        }

                        @Override
                        public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            mOnGetMessagesListener.onGetMsgFailure("Unable to get message: " + databaseError.getMessage());
                        }
                    });
                } else if (dataSnapshot.hasChild(chat_type_2)) {
                    Log.e(TAG, "getMessageFromFirebaseUser: " + chat_type_2 + " exists");
                    FirebaseDatabase.getInstance()
                            .getReference()
                            .child(Constants.ARG_CHAT_ROOMS)
                            .child(chat_type_2).addChildEventListener(new ChildEventListener() {
                        @Override
                        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                            Chat chat = dataSnapshot.getValue(Chat.class);
                            mOnGetMessagesListener.onGetMsgSuccess(chat);
                        }

                        @Override
                        public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                        }

                        @Override
                        public void onChildRemoved(DataSnapshot dataSnapshot) {

                        }

                        @Override
                        public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            mOnGetMessagesListener.onGetMsgFailure("Unable to get message: " + databaseError.getMessage());
                        }
                    });
                } else {
                    Log.e(TAG, "getMessageFromFirebaseUser: no such room available");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                mOnGetMessagesListener.onGetMsgFailure("Unable to get message: " + databaseError.getMessage());

            }
        });
    }
}
