package com.example.demo.RxJava;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import com.example.demo.R;

import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func0;
import rx.schedulers.Schedulers;

/**
 * @Author godv
 * Date on 2020/4/30  14:49
 */
public class RxAndroidActivity extends AppCompatActivity {
    private static final String TAG = "RxAndroidActivity";
    private Button button;
    private Looper looper;
    private StringBuilder stringBuilder = new StringBuilder("hah");
    private Handler handler=new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            return false;
        }
    });

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rxandroid_layout);
        initView();
        MyHandlerThread myHandlerThread=new MyHandlerThread("RxAndroidActivity");
        myHandlerThread.start();
        looper=myHandlerThread.getLooper();
    }

    private void initView() {
        button = findViewById(R.id.btn_rx);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData1();
            }
        });
    }

    /**
     *使用AndroidSchedulers
     */
    public void getData() {
        initObservable().subscribeOn(Schedulers.io())//设置事件源所在的线程
                .observeOn(AndroidSchedulers.mainThread())//订阅者所在的线程
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        Log.e(TAG, "onCompleted方法");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String s) {
                        Log.e(TAG, "onNext" + s);
                        stringBuilder.append(s);
                        //为什么只会在最后的时候进行更新UI呢
                        button.setText(stringBuilder.toString());
                    }
                });
    }

    /**
     * 使用LooperSchedulers
     */
    public void getData1() {
        initObservable().subscribeOn(AndroidSchedulers.from(looper))//设置事件源所在的线程
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        handler.sendEmptyMessage(0);
                    }
                })//订阅之前的操作
                .observeOn(AndroidSchedulers.mainThread())//订阅者所在的线程
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        Log.e(TAG, "onCompleted方法");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String s) {
                        Log.e(TAG, "onNext" + s);
                        stringBuilder.append(s);
                        //为什么只会在最后的时候进行更新UI呢
                        button.setText(stringBuilder.toString());
                    }
                });
    }


    /**
     * 初始化被观察者
     */
    private Observable<String> initObservable() {
        //延迟事件的发送时间，模拟耗时操作
        //defer 操作符与create、just、from等操作符一样，是创建类操作符，
        // 不过所有与该操作符相关的数据都是在订阅时才生效的。
        return Observable.defer(new Func0<Observable<String>>() {
            @Override
            public Observable<String> call() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //为什么依次发送12345.最后button显示最后一个
               return Observable.just("1", "2", "3", "4", "5");
            }
        });

    }


    class MyHandlerThread extends HandlerThread{

        public MyHandlerThread(String name) {
            super(name);
        }
    }

}
