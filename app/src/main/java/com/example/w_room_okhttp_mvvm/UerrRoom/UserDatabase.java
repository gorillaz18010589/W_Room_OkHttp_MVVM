package com.example.w_room_okhttp_mvvm.UerrRoom;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.w_room_okhttp_mvvm.API.MyOkHttpApi;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Response;

@Database(entities = {User.class}, version = 1)
public abstract class UserDatabase extends RoomDatabase {
    private static UserDatabase instance;
    public abstract UserDao userDao();

    private static String TAG = "hank";
    private static String MSG ="UserDatabase => ";
    private static String url="https://shop.ljz789.com/index.php?store_id=8";

    /*A.創建DB
     *@param => Context context:要執行DB的Context
     * */
    public static synchronized UserDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), UserDatabase.class, "PddUserDatabaseeee")
                    .fallbackToDestructiveMigration()
                    .addCallback(callback)
                    .build();
            Log.v(TAG, MSG + "getInstance:" + "UserDatabase:" + instance.toString());
        }

        return instance;
    }

    //B.建立CallBack在第一次創建DB時,自己定義callback
    private static RoomDatabase.Callback callback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            Log.v(TAG, MSG + "RoomDatabase:" + "onCreate:" +db.isOpen());
            new PopulateAsyncTask(instance).execute();
        }
    };

    //C.用AsyncTask在第一時間,在背景呼叫loginApi,並且將拿到Result轉成User資料存在入DB
    private static class PopulateAsyncTask extends AsyncTask<User, Void, Void> {
        private UserDao userDao;

        public PopulateAsyncTask(UserDatabase userDatabase) {
            userDao = userDatabase.userDao();
            Log.v(TAG, MSG + "PopulateAsyncTask:" + "userDao:" + userDao.toString());
        }

        @Override
        protected Void doInBackground(User... users) {
            loginApi();
            Log.v(TAG, MSG + "PopulateAsyncTask:" + "doInBackground:");
            return null;
        }

        //D.呼叫loginApi,並且將拿到Result轉成User資料存在入DB
        private void loginApi() {
            Map<String, String> map = new HashMap<>();
            map.put("module", "app");
            map.put("action", "login");
            map.put("app", "login");
            map.put("phone", "13232236359");
            map.put("password", "aaaa1111");
            map.put("cart_id", "");

            MyOkHttpApi.instance().doPostFormBody(url, map, new MyOkHttpApi.OkHttpCallBack() {
                @Override
                public void onFailure(IOException e) {
                    Log.v(TAG, MSG + "onFailure:" + e.toString());
                }

                @Override
                public void onSuccess(Response response) {
                    if (response.isSuccessful()) {
                        Gson gson = new Gson();
                        try {
                            String body = response.body().string();
                            Log.v(TAG, MSG + "onSuccess(): +/body:" + body);
                             User user  = gson.fromJson(body,User.class);
                             userDao.insert(user);
                        } catch (IOException e) {
                            Log.v(TAG, MSG + "e:" + e.toString());
                        }
                    }
                }
            });


        }

    }

}
