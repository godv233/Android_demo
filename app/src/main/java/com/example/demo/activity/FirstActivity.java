package com.example.demo.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;

import com.example.demo.R;

public class FirstActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("godv","FirstActivity onCreate()");
        setContentView(R.layout.first_activity_layout);
        Button btn = findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转下一个页面
                //显示启动
                Intent intent=new Intent(FirstActivity.this,SecondActivity.class);
                startActivity(intent);
                //隐式启动
//                Intent intent=new Intent("com.example.demo.activity.SecondActivity");
//                startActivity(intent);

            }
        });

    }
}
