package com.example.demo.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.demo.R;
import com.example.demo.entity.JsonResult;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author godv
 * Date on 2020/4/28  10:22
 */
public class ResultReAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String BASE_UTL="http://10.0.2.2:9102";
    private static final String TAG="ResultReAdapter";
    private List<JsonResult.DataBeans> data = new ArrayList<>();

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.retrofit_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        //绑定数据
        MyViewHolder myViewHolder= (MyViewHolder) holder;
        JsonResult.DataBeans beans = data.get(position);
        myViewHolder.result_title.setText(beans.getTitle());
        myViewHolder.result_name.setText(beans.getUserName());
        Log.e(TAG,beans.getCover());
        Glide.with(holder.itemView.getContext()).load(BASE_UTL+beans.getCover()).into(myViewHolder.result_cover);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(JsonResult resultObj) {
        data.clear();
        data.addAll(resultObj.getData());
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView result_cover;
        public TextView result_title;
        public TextView result_name;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            result_cover = itemView.findViewById(R.id.result_cover);
            result_title = itemView.findViewById(R.id.result_title);
            result_name = itemView.findViewById(R.id.result_name);
        }
    }
}
