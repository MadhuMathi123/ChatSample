package com.chatsample.user.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chatsample.R;
import com.chatsample.user.models.User;

import java.util.List;


public class UserRecyclerAdapter extends RecyclerView.Adapter<UserRecyclerAdapter.ViewHolder> {
    private List<User> mUsers;

    public UserRecyclerAdapter(List<User> users) {
        this.mUsers = users;
    }

    /*public void add(User user) {
        mUsers.add(user);
        notifyItemInserted(mUsers.size() - 1);
    }*/

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_all_user_listing, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        User user = mUsers.get(position);

        if (user.email != null) {
            String alphabet = user.email.substring(0, 1);

            holder.tvUserName.setText(user.email);
            holder.tvUser.setText(alphabet);
        }
    }

    @Override
    public int getItemCount() {
        if (mUsers != null) {
            return mUsers.size();
        }
        return 0;
    }

    public User getUser(int position) {
        return mUsers.get(position);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvUser, tvUserName;

        ViewHolder(View itemView) {
            super(itemView);
            tvUser = itemView.findViewById(R.id.tvUser);
            tvUserName = itemView.findViewById(R.id.tvUserName);
        }
    }
}
