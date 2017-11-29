package com.chatsample.chat;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.chatsample.R;
import com.chatsample.chat.adapter.ChatRecyclerAdapter;
import com.chatsample.chat.models.Chat;
import com.chatsample.chat.mvp.ChatContract;
import com.chatsample.chat.mvp.ChatPresenter;
import com.chatsample.utils.Constants;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;


public class ChatFragment extends Fragment implements ChatContract.view, View.OnClickListener {
    RecyclerView rvChat;
    EditText etMsg;
    Activity mActivity;
    FloatingActionButton fabSendMessage;
    private ProgressDialog mProgressDialog;

    private ChatRecyclerAdapter mChatRecyclerAdapter;

    private ChatPresenter mChatPresenter;

    public ChatFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_chat, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mActivity=getActivity();
        rvChat = view.findViewById(R.id.rvChat);
        etMsg = view.findViewById(R.id.etMsg);
        fabSendMessage = view.findViewById(R.id.fabSendMessage);
        fabSendMessage.setOnClickListener(this);
        mProgressDialog = new ProgressDialog(getActivity());
        mProgressDialog.setTitle(getString(R.string.loading));
        mProgressDialog.setMessage(getString(R.string.please_wait));
        mProgressDialog.setIndeterminate(true);

        mChatPresenter = new ChatPresenter(this);
        mChatPresenter.getMessage(FirebaseAuth.getInstance().getCurrentUser().getUid(),
                mActivity.getIntent().getStringExtra(Constants.ARG_RECEIVER_UID));
    }

    private void sendMessage() {
        String message = etMsg.getText().toString();
        String receiver = mActivity.getIntent().getStringExtra(Constants.ARG_RECEIVER);
        String receiverUid = mActivity.getIntent().getStringExtra(Constants.ARG_RECEIVER_UID);
        String sender = FirebaseAuth.getInstance().getCurrentUser().getEmail();
        String senderUid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        String receiverFirebaseToken = mActivity.getIntent().getStringExtra(Constants.ARG_FIREBASE_TOKEN);
        Chat chat = new Chat();
        chat.setSender(sender);
        chat.setReceiver(receiver);
        chat.setSenderUid(senderUid);
        chat.setReceiverUid(receiverUid);
        chat.setMessage(message);
        chat.setTimestamp(System.currentTimeMillis());
        mChatPresenter.sendMessage(getActivity().getApplicationContext(),
                chat,
                receiverFirebaseToken);
    }


    @Override
    public void onSendMessageSuccess() {
        etMsg.setText("");
        Toast.makeText(getActivity(), "Message sent", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSendMessageFailure(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onGetMessagesSuccess(Chat chat) {
        if (mChatRecyclerAdapter == null) {
            mChatRecyclerAdapter = new ChatRecyclerAdapter(new ArrayList<Chat>());
            rvChat.setAdapter(mChatRecyclerAdapter);
        }
        mChatRecyclerAdapter.add(chat);
        rvChat.smoothScrollToPosition(mChatRecyclerAdapter.getItemCount() - 1);
    }

    @Override
    public void onGetMessagesFailure(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fabSendMessage:
                sendMessage();
                break;

        }
    }
}
