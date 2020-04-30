package com.example.w_room_okhttp_mvvm.UerrRoom;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.room.RoomDatabase;

import java.util.List;

public class UserRepository {
    private LiveData<List<User>> allUsers;
    private UserDao userDao;

    private static String TAG = "hank";
    private static String MSG ="UserRepository => ";
    //A.建構式時取得實作userDao,跟取得Users的LiveData資料
    public UserRepository(Application application) {
        UserDatabase userDatabase = UserDatabase.getInstance(application); //取得資料庫
        userDao = userDatabase.userDao(); //取得interface的UserDao
        allUsers = userDao.getAllUsers(); //用userDao.取得所有資料方法
        Log.v(TAG,MSG  +"userDao:" + userDao.toString()) ;
    }

    //C.增刪修查加上方法,用直行續
    public void insert(User user) {
        new InsertAsyncTask(userDao).execute(user);
    }

    public void delete(User user) {
        new DeleteAsyncTask(userDao).execute(user);
    }

    public void update(User user) {
        new UpdateAsyncTask(userDao).execute(user);
    }

    public LiveData<List<User>> getAllUsers() {
        Log.v(TAG,MSG +"getAllUsers:" + allUsers.toString()) ;
        return allUsers;
    }

    //B.內部類別,當增刪修查需要Task
    private static class InsertAsyncTask extends AsyncTask<User, Void, Void> {
        private UserDao userDao; //宣告自己類別的UserDao

        public InsertAsyncTask(UserDao userDao) { //將UserRepository的userDao,直接在建構式時傳進來玩
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDao.insert(users[0]);
            return null;
        }
    }

    private static class UpdateAsyncTask extends AsyncTask<User, Void, Void> {
        private UserDao userDao; //宣告自己類別的UserDao

        public UpdateAsyncTask(UserDao userDao) { //將UserRepository的userDao,直接在建構式時傳進來玩
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDao.update(users[0]);
            return null;
        }
    }

    private static class DeleteAsyncTask extends AsyncTask<User, Void, Void> {
        private UserDao userDao; //宣告自己類別的UserDao

        public DeleteAsyncTask(UserDao userDao) { //將UserRepository的userDao,直接在建構式時傳進來玩
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDao.delete(users[0]);
            return null;
        }
    }


}
