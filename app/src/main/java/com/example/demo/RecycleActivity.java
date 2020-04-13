package com.example.demo;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.demo.adapter.RecycleAdapter;

import static androidx.recyclerview.widget.StaggeredGridLayoutManager.*;

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
        RecycleAdapter recycleAdapter = new RecycleAdapter(this);
        //垂直方向和水平方向的滚动列表
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
//        recyclerView.setLayoutManager(linearLayoutManager);
//        //设置间隔
//        recyclerView.addItemDecoration(new SpaceItemDecoration(50));
//        recyclerView.setAdapter(recycleAdapter);
        //网格布局
//        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,3);
//        recyclerView.setLayoutManager(gridLayoutManager);
//        recyclerView.addItemDecoration(new SpaceItemDecoration(20));
//        recyclerView.setAdapter(recycleAdapter);
        //瀑布流 需要设置加载不同的图片才能偶效果 固定图片  就和网格一样了
        StaggeredGridLayoutManager staggeredGridLayoutManager=new StaggeredGridLayoutManager(2, VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        recyclerView.setAdapter(recycleAdapter);
        recyclerView.addItemDecoration(new SpaceItemDecoration(20));
    }
}
