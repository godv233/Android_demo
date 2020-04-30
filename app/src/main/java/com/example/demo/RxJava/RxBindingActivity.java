package com.example.demo.RxJava;

import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.demo.R;
import com.google.android.material.snackbar.Snackbar;
import com.jakewharton.rxbinding.view.RxView;

import java.util.concurrent.TimeUnit;

import rx.Subscription;
import rx.functions.Action1;

/**
 * @Author godv
 * Date on 2020/4/30  16:04
 */
public class RxBindingActivity extends AppCompatActivity {
    private Button button;
    private int i = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rxbinding_layout);
        initViewAndListen();
    }

    private void initViewAndListen() {
        button = findViewById(R.id.btnRxBinding);
        //其中还有各种api 可以用
        //点击监听
        //相当于使用RxView来设置监听事件
        //throttleFirst可以防抖动。也就是短时间发送多次的时候，只会处理一次
        //Subscription用于接触绑定关系的。当我们的页面销毁之后，不解除绑定会造成内存泄露
        Subscription subscribe = RxView.clicks(button).throttleFirst(3000, TimeUnit.MILLISECONDS)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        Snackbar.make(button, "发送了" + ++i + "个事件", Snackbar.LENGTH_SHORT).show();
                    }
                });
        //解除绑定
        subscribe.isUnsubscribed();
    }
}
