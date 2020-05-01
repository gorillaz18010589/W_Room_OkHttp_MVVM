package com.example.w_room_okhttp_mvvm.GetMoreRoom;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.w_room_okhttp_mvvm.UerrRoom.User;

import java.util.List;

@Dao
public interface GetMoreDao {
    @Insert
    void insert(GetMoreResult getMoreResult);

    @Delete
    void delete(GetMoreResult getMoreResult);

    @Update
    void update(GetMoreResult getMoreResult);

    @Query("SELECT * FROM  get_more_table7 ORDER BY id ")
    LiveData<List<GetMoreResult>> getMoreResults();
}
