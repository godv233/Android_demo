package com.example.demo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.demo.R;
import com.example.demo.entity.News;

import java.util.List;

public class NewsAdapter extends BaseAdapter {
    private List<News> data;
    private Context context;

    public NewsAdapter(List<News> data, Context context) {
        this.data=data;
        this.context=context;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView= LayoutInflater.from(context).inflate(R.layout.listview_item,parent,false);
        ImageView imageView=convertView.findViewById(R.id.img_icon);
        TextView title=convertView.findViewById(R.id.tv_title);
        TextView content=convertView.findViewById(R.id.tv_content);
        imageView.setBackgroundResource(data.get(position).getIcon());
        title.setText(data.get(position).getTitle());
        content.setText(data.get(position).getContent());
        return convertView;
    }
    //优化BaseAdapter
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//            ViewHolder holder=null;
//            if (convertView==null){
//                holder=new ViewHolder();
//                convertView=LayoutInflater.from(context).inflate(R.layout.listview_item,parent,false);
//                holder.imgIcon=convertView.findViewById(R.id.img_icon);
//                holder.title=convertView.findViewById(R.id.tv_title);
//                holder.content=convertView.findViewById(R.id.tv_content);
//            }else{
//                holder= (ViewHolder) convertView.getTag();
//            }
//            holder.imgIcon.setBackgroundResource(data.get(position).getIcon());
//            holder.title.setText(data.get(position).getTitle());
//            holder.content.setText(data.get(position).getContent());
//            return convertView;
//    }
//    static class ViewHolder{
//        ImageView imgIcon;
//        TextView title;
//        TextView content;
//    }
}
