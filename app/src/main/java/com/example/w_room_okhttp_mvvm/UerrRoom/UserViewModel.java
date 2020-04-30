package com.example.w_room_okhttp_mvvm.UerrRoom;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.Map;

public class UserViewModel extends AndroidViewModel {
    private LiveData<List<User>> allUsers;
    private UserRepository userRepository;
    private static String TAG = "hank";
    private static String MSG ="UserViewModel => ";

    //A.AndroidViewModel的建構式會把application參數帶進來,藥用UserRepository物件,需要Application
    public UserViewModel(@NonNull Application application) {
        super(application);
        userRepository = new UserRepository(application);
        allUsers = userRepository.getAllUsers(); //取得所有資料
        Log.v(TAG, MSG +"UserViewModel():" +"userRepository:" + userRepository.toString());
    }

    //B.用藥用UserRepository物件的增刪修查方法
    public void insert(User user) {
        userRepository.insert(user);
    }

    public void delete(User user) {
        userRepository.delete(user);
    }
    public void update(User user){
        userRepository.update(user);
    }
    //C.回傳有所有資料的LiveData
    public LiveData<List<User>> getAllUsers() {
        return allUsers;
    }
}
