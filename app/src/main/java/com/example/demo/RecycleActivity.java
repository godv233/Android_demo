package com.example.demo;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demo.adapter.RecycleAdapter;

/**
 * @Author godv
 * Date on 2020/4/13  21:59
 */
public class RecycleActivity extends AppCompatActivity {
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycle_layout);
        recyclerView = findViewById(R.id.recycleView);
        //垂直方向和水平方向的滚动列表
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        //设置间隔
        recyclerView.addItemDecoration(new SpaceItemDecoration(50));
        RecycleAdapter recycleAdapter = new RecycleAdapter(this);
        recyclerView.setAdapter(recycleAdapter);
    }
}
