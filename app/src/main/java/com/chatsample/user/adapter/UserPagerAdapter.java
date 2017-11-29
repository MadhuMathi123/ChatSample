package com.chatsample.user.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.chatsample.user.UserFragment;

/**
 * Created by Ocs pl-79(17.2.2016) on 11/27/2017.
 */

public class UserPagerAdapter extends FragmentPagerAdapter {

    private static final Fragment[] sFragments = new Fragment[]{/*UsersFragment.newInstance(UsersFragment.TYPE_CHATS),*/
            UserFragment.newInstance(UserFragment.TYPE_ALL)};
    private static final String[] sTitles = new String[]{/*"Chats",*/
            "All Users"};

    public UserPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return sFragments[position];
    }

    @Override
    public int getCount() {
        return sFragments.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return sTitles[position];
    }
}
