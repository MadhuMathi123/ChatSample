package com.chatsample.chat;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.chatsample.R;
import com.chatsample.utils.ChatSample;
import com.chatsample.utils.Constants;

public class ChatActivity extends AppCompatActivity {
    private Toolbar toolbarChat;

    public static void startActivity(Context context,
                                     String receiver,
                                     String receiverUid,
                                     String firebaseToken) {
        Intent intent = new Intent(context, ChatActivity.class);
        intent.putExtra(Constants.ARG_RECEIVER, receiver);
        intent.putExtra(Constants.ARG_RECEIVER_UID, receiverUid);
        intent.putExtra(Constants.ARG_FIREBASE_TOKEN, firebaseToken);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        toolbarChat = findViewById(R.id.toolbarChat);
        setSupportActionBar(toolbarChat);

        // set toolbar title
        toolbarChat.setTitle(getIntent().getExtras().getString(Constants.ARG_RECEIVER));

    }

    @Override
    protected void onResume() {
        super.onResume();
        ChatSample.setChatActivityOpen(true);
    }

    @Override
    protected void onPause() {
        super.onPause();
        ChatSample.setChatActivityOpen(false);
    }

}
