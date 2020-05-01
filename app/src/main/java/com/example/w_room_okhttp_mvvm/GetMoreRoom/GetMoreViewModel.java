package com.example.w_room_okhttp_mvvm.GetMoreRoom;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class GetMoreViewModel extends AndroidViewModel {
    private GetMoreRepository getMoreRepository;
    private LiveData<List<GetMoreResult>> allMores;
    private static String TAG = "hank";
    private static String MSG ="UserViewModel => ";

    public GetMoreViewModel(@NonNull Application application) {
        super(application);
        getMoreRepository = new GetMoreRepository(application);
        allMores = getMoreRepository.query();
        Log.v(TAG, MSG +"UserViewModel():" +"userRepository:" + getMoreRepository.toString());


    }

    public void insert(GetMoreResult getMoreResult){
        getMoreRepository.insert(getMoreResult);
    }

    public void delete(GetMoreResult getMoreResult){
        getMoreRepository.delete(getMoreResult);
    }

    public void update(GetMoreResult getMoreResult){
        getMoreRepository.update(getMoreResult);
    }

    //這個LiveData取得所有資料
    public LiveData<List<GetMoreResult>> getAllMores(){
        return allMores;
    }

}
