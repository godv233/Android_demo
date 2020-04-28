package com.example.demo.adapter.ResultAdapter;

import android.text.TextUtils;
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
import com.example.demo.entity.MomentItem;


import java.util.ArrayList;
import java.util.List;

/**
 * @Author godv
 * Date on 2020/4/27  16:45
 */
public class ResultAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<MomentItem.DataBean.ContentBean> mData = new ArrayList<>();

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_text, parent, false);
        return new MyHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyHolder myHolder= (MyHolder) holder;
        MomentItem.DataBean.ContentBean contentBean = mData.get(position);
        String subTitle = contentBean.getSubTitle();
        if (!TextUtils.isEmpty(subTitle)) {
            myHolder.moment_title.setText(subTitle);
        } else {
            myHolder.moment_title.setText(contentBean.getContent());
        }
        myHolder.user_name.setText(contentBean.getUserName());
        myHolder.user_info.setText(contentBean.getPosition() + "@" + contentBean.getCompany());
        Log.e("ResultAdapter",contentBean.getUserAvatar());
        //图片我们先借助于Glide库
        Glide.with(holder.itemView.getContext()).load(contentBean.getUserAvatar()).into(((MyHolder) holder).user_avatar);

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setData(MomentItem result) {
        mData.clear();
        mData.addAll(result.getData().getContent());
        notifyDataSetChanged();
    }

    static class MyHolder extends RecyclerView.ViewHolder {
        public ImageView user_avatar;
        public TextView user_name;
        public TextView user_info;
        public TextView moment_title;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            user_avatar = itemView.findViewById(R.id.user_avatar);
            user_name = itemView.findViewById(R.id.user_name);
            user_info = itemView.findViewById(R.id.user_info);
            moment_title = itemView.findViewById(R.id.moment_title);
        }

    }
}
