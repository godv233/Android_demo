package com.example.demo;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.demo.adapter.NewsAdapter;
import com.example.demo.entity.News;

import java.util.LinkedList;
import java.util.List;

public class ListViewActivity  extends AppCompatActivity {
    private List<News> data;
    private Context context;
    private NewsAdapter newsAdapter;
    private ListView listView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adapter);
        context=this;
        listView=findViewById(R.id.listView);
        data=new LinkedList<News>();
        for (int i = 0; i < 10; i++) {
            data.add(new News("新闻标题"+i,"新闻内容"+i,R.mipmap.ic_launcher));
        }
        newsAdapter=new NewsAdapter(data,context);
        listView.setAdapter(newsAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(context,"点击了第"+position+"条数据",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
