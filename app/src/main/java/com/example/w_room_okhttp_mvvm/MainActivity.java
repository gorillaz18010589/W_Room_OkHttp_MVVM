package com.example.w_room_okhttp_mvvm;
//1.User => @Entity:創建表欄位
//2.UserDao => @Dao =>增刪修查方法Interface
/*3.UserDatabase = > @Database
* A.創建DB =>UserDatabase.getInstance
 *@param => Context context:要執行DB的Context
 * */
//4.UserRepository => 實作UserDao執行續增,刪.修,查
/*5.回到UserDatabase => 自訂CallBack,在DB,OnCreate時 ,自己寫執行續新增資料進去
* B.建立CallBack在第一次創建DB時,自己定義callback
* C.用AsyncTask在第一時間,在背景呼叫loginApi,並且將拿到Result轉成User資料存在入DB
* D.呼叫loginApi,並且將拿到Result轉成User資料存在入DB
 * */
//6.設置Adapter自己寫setUsers,讓LiveData取得DB資料時,LiveData的OnChange時使用,去更新notifyDataSetChanged();
//7.LiveData取得DB資料時,使用Adapter,將取得的User資料傳送到RecyclerView去作呈現,更新


import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.w_room_okhttp_mvvm.UerrRoom.User;
import com.example.w_room_okhttp_mvvm.UerrRoom.UserDatabase;
import com.example.w_room_okhttp_mvvm.UerrRoom.UserRecyclerViewAdpapter;
import com.example.w_room_okhttp_mvvm.UerrRoom.UserViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private UserViewModel userViewModel;
    private String TAG = "hank";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        //A.設定好UserAdapter做畫面呈現
        final RecyclerView recyclerView = findViewById(R.id.recy);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        final UserRecyclerViewAdpapter userRecyclerViewAdapter = new UserRecyclerViewAdpapter();
        recyclerView.setAdapter(userRecyclerViewAdapter);


        //B.LiveData取得DB資料時,使用Adapter,將取得的User資料傳送到RecyclerView去作呈現,更新
        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        userViewModel.getAllUsers().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                Log.v(TAG, "observe =>onChanged:" + users.toString());
                userRecyclerViewAdapter.setUsers(users);
            }
        });


    }
}
