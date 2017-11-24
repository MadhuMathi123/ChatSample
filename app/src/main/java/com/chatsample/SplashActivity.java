package com.chatsample;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {
    private static final int SPLASH_TIME_DELAY = 3000;
    private Handler handler;
    private Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {

            }
        };
        handler.postDelayed(runnable, SPLASH_TIME_DELAY);

    }
}
