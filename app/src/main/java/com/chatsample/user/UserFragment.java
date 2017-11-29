package com.chatsample.user;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.chatsample.R;
import com.chatsample.chat.ChatActivity;
import com.chatsample.user.adapter.UserRecyclerAdapter;
import com.chatsample.user.models.User;
import com.chatsample.user.mvp.getUser.GetUserContract;
import com.chatsample.user.mvp.getUser.GetUserPresenter;
import com.chatsample.utils.ItemClickSupport;

import java.util.List;


/**
 * A placeholder fragment containing a simple view.
 */
public class UserFragment extends Fragment implements GetUserContract.view, SwipeRefreshLayout.OnRefreshListener, ItemClickSupport.OnItemClickListener {
    public static final String ARG_TYPE = "type";
    public static final String TYPE_CHATS = "type_chats";
    public static final String TYPE_ALL = "type_all";

    private SwipeRefreshLayout srlUserList;
    private RecyclerView rvUserList;

    private UserRecyclerAdapter mUserRecyclerAdapter;

    GetUserPresenter getUserPresenter;


    public static UserFragment newInstance(String type) {
        Bundle args = new Bundle();
        args.putString(ARG_TYPE, type);
        UserFragment fragment = new UserFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_user, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        srlUserList = view.findViewById(R.id.srlUserList);
        rvUserList = view.findViewById(R.id.rvUserList);
        getUserPresenter = new GetUserPresenter(this);
        getUsers();
        srlUserList.post(new Runnable() {
            @Override
            public void run() {
                srlUserList.setRefreshing(true);
            }
        });

        ItemClickSupport.addTo(rvUserList)
                .setOnItemClickListener(this);

        srlUserList.setOnRefreshListener(this);

    }

    private void getUsers() {
        getUserPresenter.getAllUser();

    }

    @Override
    public void success(List<User> users) {
        srlUserList.post(new Runnable() {
            @Override
            public void run() {
                srlUserList.setRefreshing(false);
            }
        });
        mUserRecyclerAdapter = new UserRecyclerAdapter(users);
        rvUserList.setAdapter(mUserRecyclerAdapter);
        mUserRecyclerAdapter.notifyDataSetChanged();
    }

    @Override
    public void failure(String message) {
        srlUserList.post(new Runnable() {
            @Override
            public void run() {
                srlUserList.setRefreshing(false);
            }
        });
        Toast.makeText(getActivity(), "Error: " + message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRefresh() {
        getUsers();
    }

    @Override
    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
        ChatActivity.startActivity(getActivity(),
                mUserRecyclerAdapter.getUser(position).email,
                mUserRecyclerAdapter.getUser(position).uid,
                mUserRecyclerAdapter.getUser(position).firebaseToken);
    }
}
