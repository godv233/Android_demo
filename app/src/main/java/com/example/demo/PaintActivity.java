package com.example.demo;

import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.demo.view.PaintView;

/**
 * @Author godv
 * Date on 2020/4/14  15:27
 */
public class PaintActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.paintview);
        //布局管理器
        LinearLayout manager=findViewById(R.id.linearLayout);
        manager.addView(new PaintView(this));
    }
}
