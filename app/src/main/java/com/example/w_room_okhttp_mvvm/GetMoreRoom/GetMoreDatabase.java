package com.example.w_room_okhttp_mvvm.GetMoreRoom;

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

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Response;

@Database(entities = GetMoreResult.class, version = 1)
public abstract class GetMoreDatabase extends RoomDatabase {
    private static GetMoreDatabase instance;
    public abstract GetMoreDao getMoreDao();
    private static String url ="https://shop.ljz789.com/index.php?store_id=8";

    private static String TAG = "hank";
    private static String MSG ="UserDatabase => ";

    public static GetMoreDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),GetMoreDatabase.class,"get_more_db7")
                    .fallbackToDestructiveMigration()
                    .addCallback(callback)
                    .build();
            Log.v(TAG, MSG + "getInstance:" + "UserDatabase:" + instance.toString());

        }
        return instance;
    }

    private static RoomDatabase.Callback callback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            Log.v(TAG, MSG + "RoomDatabase:" + "onCreate:" +db.isOpen());
            new PopulateAsyncTask(instance).execute();
        }
    };

    private static class PopulateAsyncTask extends AsyncTask<GetMoreResult, Void, Void> {
        private GetMoreDao getMoreDao;

        public PopulateAsyncTask(GetMoreDatabase getMoreDatabase) {
            getMoreDao = getMoreDatabase.getMoreDao();
            Log.v(TAG, MSG + "PopulateAsyncTask:" + "userDao:" + getMoreDao.toString());

        }


        @Override
        protected Void doInBackground(GetMoreResult... getMoreResults) {
            getMoreApi();
            return null;
        }

        public void getMoreApi() {
            final Map<String, String> map = new HashMap<>();
            map.put("module", "app");
            map.put("action", "index");
            map.put("app", "get_more");
            map.put("page", "0");
            MyOkHttpApi.instance().doPostFormBody(url, map, new MyOkHttpApi.OkHttpCallBack() {
                @Override
                public void onFailure(IOException e) {

                }

                @Override
                public void onSuccess(Response response) {
                    if (response.isSuccessful()) {
                        Gson gson = new Gson();
                        try {
                            String body = response.body().string();
                            JSONObject jsonObject = new JSONObject(body);
                            String data = jsonObject.getString("data");
                            JSONArray jsonArray = new JSONArray(data);
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsobject = jsonArray.getJSONObject(i);
                                String json = jsobject.toString();
                                GetMoreResult getMoreResult = gson.fromJson(json, GetMoreResult.class);
                                Log.v(TAG, "getMoreResult:" + "現在name:" + getMoreResult.getName());
                                getMoreDao.insert(getMoreResult);
                            }

                        } catch (Exception e) {

                        }
                    }
                }
            });
        }

    }

}
//      try {
//              String json = MyOkHttpApi.getStringJson(response.body().string(),"data");
//              Gson gson = new Gson();
//              GetMoreResult getMoreResult = gson.fromJson(json, GetMoreResult.class);
//        getMoreDao.insert(getMoreResult);
//
//        } catch (IOException e) {
//        e.printStackTrace();
//        }