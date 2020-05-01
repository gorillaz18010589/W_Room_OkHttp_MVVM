package com.example.w_room_okhttp_mvvm.GetMoreRoom;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;

import java.util.List;

public class GetMoreRepository {
    private GetMoreDao moreDao;
    private LiveData<List<GetMoreResult>> allGetMores;

    private static String TAG = "hank";
    private static String MSG ="UserRepository => ";

    public GetMoreRepository(Application application){
         GetMoreDatabase getMoreDatabase = GetMoreDatabase.getInstance(application);
         moreDao = getMoreDatabase.getMoreDao();
         allGetMores = moreDao.getMoreResults();
        Log.v(TAG,MSG  +"userDao:" + moreDao.toString()) ;

    }

    public void insert(GetMoreResult getMoreResult){
        new InsertAsyncTask(moreDao).execute(getMoreResult);
    }
    public void delete(GetMoreResult getMoreResult){
        new DeleteAsyncTask(moreDao).execute(getMoreResult);
    }
    public void update(GetMoreResult getMoreResult){
        new UpdateAsyncTask(moreDao).execute(getMoreResult);
    }
    public LiveData<List<GetMoreResult>> query(){
        Log.v(TAG,MSG +"getAllUsers:" + allGetMores.toString()) ;
        return allGetMores;
    }

    private static class InsertAsyncTask  extends AsyncTask<GetMoreResult,Void,Void>{
        private GetMoreDao getMoreDao;

        public InsertAsyncTask (GetMoreDao getMoreDao){
            this.getMoreDao = getMoreDao;
        }

        @Override
        protected Void doInBackground(GetMoreResult... getMoreResults) {
            getMoreDao.insert(getMoreResults[0]);
            return null;
        }
    }

    private static class UpdateAsyncTask  extends AsyncTask<GetMoreResult,Void,Void>{
        private GetMoreDao getMoreDao;

        public UpdateAsyncTask (GetMoreDao getMoreDao){
            this.getMoreDao = getMoreDao;
        }

        @Override
        protected Void doInBackground(GetMoreResult... getMoreResults) {
            getMoreDao.update(getMoreResults[0]);
            return null;
        }
    }

    private static class DeleteAsyncTask  extends AsyncTask<GetMoreResult,Void,Void>{
        private GetMoreDao getMoreDao;

        public DeleteAsyncTask (GetMoreDao getMoreDao){
            this.getMoreDao = getMoreDao;
        }

        @Override
        protected Void doInBackground(GetMoreResult... getMoreResults) {
            getMoreDao.delete(getMoreResults[0]);
            return null;
        }
    }

}
