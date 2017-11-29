package com.chatsample.chat.mvp;

import android.content.Context;

import com.chatsample.chat.models.Chat;

/**
 * Created by Ocs pl-79(17.2.2016) on 11/27/2017.
 */

public interface ChatContract {
    interface view {
        void onSendMessageSuccess();

        void onSendMessageFailure(String message);

        void onGetMessagesSuccess(Chat chat);

        void onGetMessagesFailure(String message);
    }

    interface presenter {
        void sendMessage(Context context,Chat chat,String receiverFirebaseToken);

        void getMessage(String senderUid,String receiverUid);

    }

    interface interactor {
        void sendMessageToFirebaseUser(Context context, Chat chat, String receiverFirebaseToken);

        void getMessageFromFirebaseUser(String senderUid, String receiverUid);
    }

    interface onSendMsgListener {
        void onSendMsgSuccess();

        void onSendMsgFailure(String Message);
    }

    interface onGetMsgListener {
        void onGetMsgSuccess(Chat chat);

        void onGetMsgFailure(String Message);

    }
}
