package com.example.demo.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.demo.R;

public class SecondActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("godv","SecondActivity onCreate()");
        setContentView(R.layout.second_activity_layout);
        //接收前一个activity的值
        Intent intent = getIntent();
        Log.e("godv",intent.getStringExtra("godv"));
        //返回给上一个activity
        Intent back=new Intent();
        back.putExtra("text","小伟");
        setResult(1002,back);
    }
}
