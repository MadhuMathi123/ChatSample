package com.chatsample.utils;

import android.app.Application;

import com.google.firebase.FirebaseApp;

/**
 * Created by Ocs pl-79(17.2.2016) on 11/27/2017.
 */

public class ChatSample extends Application {
    private static boolean sIsChatActivityOpen = false;

    public static boolean isChatActivityOpen() {
        return sIsChatActivityOpen;
    }

    public static void setChatActivityOpen(boolean isChatActivityOpen) {
        ChatSample.sIsChatActivityOpen = isChatActivityOpen;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseApp.initializeApp(this);
    }
}
