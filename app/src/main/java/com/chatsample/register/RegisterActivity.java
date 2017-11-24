package com.chatsample.register;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.chatsample.R;

public class RegisterActivity extends AppCompatActivity {

    Toolbar toolbarRegister;

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, RegisterActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        bindViews();
        init();
    }
    private void bindViews() {
        toolbarRegister=findViewById(R.id.toolbarRegister);
    }


    private void init() {
        setSupportActionBar(toolbarRegister);
    }


}
