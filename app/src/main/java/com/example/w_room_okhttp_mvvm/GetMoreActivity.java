package com.example.w_room_okhttp_mvvm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.w_room_okhttp_mvvm.GetMoreRoom.GetMoreRecyclerViewAdapter;
import com.example.w_room_okhttp_mvvm.GetMoreRoom.GetMoreResult;
import com.example.w_room_okhttp_mvvm.GetMoreRoom.GetMoreViewModel;

import java.util.List;

public class GetMoreActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private GetMoreViewModel getMoreViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_more);

        init();
    }

    private void init() {
        recyclerView = findViewById(R.id.rev);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        int width = getWindowManager().getDefaultDisplay().getWidth();

        final GetMoreRecyclerViewAdapter getMoreRecyclerViewAdapter = new GetMoreRecyclerViewAdapter(this,width,300);
        recyclerView.setAdapter(getMoreRecyclerViewAdapter);


        getMoreViewModel = ViewModelProviders.of(this).get(GetMoreViewModel.class);
        getMoreViewModel.getAllMores().observe(this, new Observer<List<GetMoreResult>>() {
            @Override
            public void onChanged(List<GetMoreResult> getMoreResults) {
                Log.v("hank", "sie:" + String.valueOf(getMoreRecyclerViewAdapter.getItemCount()));
                getMoreRecyclerViewAdapter.setGetMores(getMoreResults);
            }
        });
    }
}
