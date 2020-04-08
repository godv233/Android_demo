package com.example.demo;

import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ProgressBarActivity extends AppCompatActivity {
    private ProgressBar progressBar;
    int maxProgress;
    int curProgress;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.progressbar);
        progressBar=findViewById(R.id.pb);
        maxProgress=progressBar.getMax();
    }


    @Override
    protected void onStart() {
        super.onStart();
        //模拟线程启动
        new Thread(){
            @Override
            public void run() {
                while(true){
                    try {
                        for (int i = 0; i <= 100; i++) {
                            Thread.sleep(1000);
                            curProgress+=10;
                            if (curProgress>maxProgress){
                                break;
                            }
                            progressBar.setProgress(curProgress);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }
}
