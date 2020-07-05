package com.example.bukuwarung;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    private Context context;
    private List<Users> usersList;

    private OnUsersListener mOnUsersListener;

    public UserAdapter(Context ct, List<Users> usersList, OnUsersListener onUsersListener) {
        context = ct;
        this.usersList = usersList;
        this.mOnUsersListener = onUsersListener;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_users, viewGroup, false);
        return new UserViewHolder(view, mOnUsersListener);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder searchViewHolder, int i) {
        String name = usersList.get(i).getFirstName() + " " + usersList.get(i).getLastName();
        searchViewHolder.tvName.setText(name);
        Glide.with(context).load(usersList.get(i).getAvatar()).into(searchViewHolder.ivUser);
    }
    @Override
    public int getItemCount() {
        return usersList.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tvName;
        ImageView ivUser;
        OnUsersListener mOnUserListener;

        public UserViewHolder(@NonNull View itemView, OnUsersListener onUsersListener) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            ivUser = itemView.findViewById(R.id.ivUser);
            mOnUserListener = onUsersListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mOnUserListener.onUsersClick(getAdapterPosition());
        }
    }

    public interface OnUsersListener{
        void onUsersClick(int position);
    }
}
