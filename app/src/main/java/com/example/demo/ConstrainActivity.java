package com.example.demo;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

/**
 * @Author godv
 * Date on 2020/4/13  21:21
 * 约束性布局
 */
public class ConstrainActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.constrain_layout);
    }
}
