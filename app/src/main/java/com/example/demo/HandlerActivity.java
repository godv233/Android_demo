package com.example.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * @Author godv
 * Date on 2020/4/21  21:57
 */
public class HandlerActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void testHandler(View v) {
        startActivity(new Intent(this, HandlerTestActivity.class));
    }
//
//    public void handlerDemo(View v) {
//        startActivity(new Intent(this, HandlerDemoActivity.class));
//    }
//
//    public void testAsyncTask(View v) {
//        startActivity(new Intent(this, AsyncTaskTestActivity.class));
//    }
}
