package com.chatsample.user;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.chatsample.R;
import com.chatsample.user.adapter.UserPagerAdapter;

public class UserActivity extends AppCompatActivity {
    private Toolbar toolbarUser;
 /*   private TabLayout tlUserList;
    private ViewPager vpUserList;*/

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, UserActivity.class);
        context.startActivity(intent);
    }

    public static void startActivity(Context context, int flags) {
        Intent intent = new Intent(context, UserActivity.class);
        intent.setFlags(flags);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        bindViews();
        init();
    }

    private void bindViews() {
        toolbarUser = findViewById(R.id.toolbarUser);
       /* tlUserList = findViewById(R.id.tlUserList);
        vpUserList = findViewById(R.id.vpUserList);*/
    }

    private void init() {
        // set the toolbar
        setSupportActionBar(toolbarUser);

        // set the view pager adapter
      /*  UserPagerAdapter userListingPagerAdapter = new UserPagerAdapter(getSupportFragmentManager());
        vpUserList.setAdapter(userListingPagerAdapter);

        // attach tab layout with view pager
        tlUserList.setupWithViewPager(vpUserList);
*/
//        mLogoutPresenter = new LogoutPresenter(this);
    }

}
