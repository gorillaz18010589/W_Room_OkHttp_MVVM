package com.example.w_room_okhttp_mvvm.UerrRoom;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.w_room_okhttp_mvvm.R;

import java.util.ArrayList;
import java.util.List;

public class UserRecyclerViewAdpapter extends RecyclerView.Adapter<UserRecyclerViewAdpapter.UserViewHolder> {
    private List<User> mData = new ArrayList<>();
    private String TAG ="hank";

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item,parent,false);
        UserViewHolder userViewHolder = new UserViewHolder(view);
        return userViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        holder.txtUserName.setText(mData.get(position).userName);
        holder.txtmessage.setText(mData.get(position).message);
        Log.v(TAG,"onBindViewHolder =>");
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    //**自己寫setUsers,讓LiveData取得DB的ˊ資料時,LiveData的OnChange時使用,去更新notifyDataSetChanged();
    public void setUsers(List<User> users){
        this.mData = users;
        notifyDataSetChanged();
        Log.v(TAG,"UserRecyclerViewAdpapter =>" +"setUsers()");

    }

    public static class UserViewHolder extends RecyclerView.ViewHolder{
        private TextView txtId,txtUserName,txtmessage;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            txtId = itemView.findViewById(R.id.txtTitle);
            txtUserName = itemView.findViewById(R.id.txtDescription);
            txtmessage = itemView.findViewById(R.id.txtPriority);
        }
    }
}
