package com.chatsample.chat.mvp;

import android.content.Context;

import com.chatsample.chat.models.Chat;

public class ChatPresenter implements ChatContract.presenter, ChatContract.onGetMsgListener, ChatContract.onSendMsgListener {

    ChatInteractor chatInteractor;
    ChatContract.view chatView;

    public ChatPresenter(ChatContract.view chatView) {
        this.chatView = chatView;
        chatInteractor = new ChatInteractor(this, this);
    }

    @Override
    public void sendMessage(Context context, Chat chat, String receiverFirebaseToken) {
        chatInteractor.sendMessageToFirebaseUser(context, chat, receiverFirebaseToken);
    }

    @Override
    public void getMessage(String senderUid, String receiverUid) {
        chatInteractor.getMessageFromFirebaseUser(senderUid,receiverUid);

    }

    @Override
    public void onSendMsgSuccess() {
        chatView.onSendMessageSuccess();

    }

    @Override
    public void onSendMsgFailure(String Message) {
        chatView.onSendMessageFailure(Message);
    }

    @Override
    public void onGetMsgSuccess(Chat chat) {
        chatView.onGetMessagesSuccess(chat);

    }

    @Override
    public void onGetMsgFailure(String Message) {
        chatView.onGetMessagesFailure(Message);

    }
}
