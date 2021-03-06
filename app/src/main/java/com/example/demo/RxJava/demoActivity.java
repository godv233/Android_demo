package com.example.demo.RxJava;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.androidnetworking.AndroidNetworking;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.observers.Observers;
import rx.schedulers.Schedulers;

/**
 * @Author godv
 * Date on 2020/4/29  14:25
 */
public class demoActivity extends AppCompatActivity {
    private static final String TAG = "demoActivity";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    /**
     * 测试lambda
     */
    private void lambdaTest() {
        new Thread(() -> Log.e(TAG, "测试lambda")).start();
    }

    /**
     * 简单创建
     */
    private void test01() {
        //被观察者 Observable
        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                //传递的方法和参数
                subscriber.onNext("hello");
                subscriber.onNext("godv");
                subscriber.onCompleted();
            }
        });
        //观察者
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onCompleted() {
                Log.e(TAG, "观察者的onCompleted方法");
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "观察者的onError方法");
            }

            @Override
            public void onNext(String s) {
                Log.e(TAG, "onNext传入--->" + s);
            }
        };
        //订阅操作
        observable.subscribe(observer);
    }

    /**
     * from()方法和just()方法
     * from()方法创建事件队列。例： 可以把数组拆分具体对象后依次发送
     * 我们还可以在订阅的时候 只选择订阅其中的一种 error complate和next  在三事件发生的可执行其中一种。
     */
    private void test02() {
        String[] arr = new String[]{"1", "2", "3"};
//        Observable<String> observable=Observable.from(arr);
        //等同于
        Observable<String> observable = Observable.just("1", "2", "3");

        //观察者
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onCompleted() {
                Log.e(TAG, "观察者的onCompleted方法");
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "观察者的onError方法");
            }

            @Override
            public void onNext(String s) {
                Log.e(TAG, "onNext传入--->" + s);
            }
        };
        //订阅操作
        observable.subscribe(observer);
    }

    /**
     * Scheduler的api的基本使用
     */
    public void test03() {
        Observable.just(getData())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<Integer>>() {
                    @Override
                    public void call(List<Integer> list) {
                        //更新UI
                        for (int i = 0; i < 10; i++) {
                            Log.e(TAG, String.valueOf(list.get(i)));
                        }
                    }
                });
    }

    /**
     * 模拟一个耗时的IO操作
     *
     * @return
     */
    public List<Integer> getData() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return list;
    }
}
