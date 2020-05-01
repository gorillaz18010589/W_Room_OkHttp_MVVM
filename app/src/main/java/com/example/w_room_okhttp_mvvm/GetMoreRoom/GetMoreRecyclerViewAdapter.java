package com.example.w_room_okhttp_mvvm.GetMoreRoom;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.w_room_okhttp_mvvm.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class GetMoreRecyclerViewAdapter extends RecyclerView.Adapter<GetMoreRecyclerViewAdapter.RecyclerViewHolder> {
    private List<GetMoreResult> mData = new ArrayList<>();
    private Context context;
    private int width,height;
    private String TAG ="hank";


    public GetMoreRecyclerViewAdapter(Context context,int width , int height) {
        this.context = context;
        this.width = width;
        this.height = height;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_getmore,parent,false);
        RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(view);
        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
//        holder.img.setImageURI(Uri.parse(mData.get(position).getImgurl()));
        Picasso.with(context).load(mData.get(position).getImgurl()).resize(width,height).rotate(2000,50,50) .into(holder.img);
        holder.txtName.setText(mData.get(position).getName());
        holder.txtPrice.setText(mData.get(position).getPrice());
        Log.v(TAG,"onBindViewHolder =>");

    }

    public void setGetMores(List<GetMoreResult> getMoreResults){
        this.mData = getMoreResults;
        for(GetMoreResult getMoreResult : mData){
            Log.v(TAG,"setGetMores:" + getMoreResult.getName());
        }
        notifyDataSetChanged();


    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder{
        private ImageView img;
        private TextView txtName, txtPrice;
        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
            txtPrice = itemView.findViewById(R.id.txtPrice);
            img = itemView.findViewById(R.id.img);
        }
    }
}
