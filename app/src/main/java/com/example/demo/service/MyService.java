package com.example.demo.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

/**
 * @Author godv
 * Date on 2020/4/12  14:34
 */
public class MyService extends Service {
    private final String TAG = "service";
    private int count;
    private boolean flag;
    //定义onBind方法返回的对象
    private MyBinder binder=new MyBinder();
     class MyBinder extends Binder{ int getCount(){
            return count;
        }
    }
    //绑定时回调该方法
    @Override
    public IBinder onBind(Intent intent) {
        Log.e(TAG,"onBind方法被调用");
        return binder;
    }
    //创建时被调用
    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG, "onCreate方法被调用!");
        new Thread(){
            @Override
            public void run() {
                while(!flag){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    count++;
                    Log.e(TAG, "count = " + count);
                }
            }
        }.start();
    }
    //断开连接时回调
    @Override
    public boolean onUnbind(Intent intent) {
        Log.e(TAG, "onUnbind方法被调用!");
        return true;
    }
    //service被关闭前回调
    @Override
    public void onDestroy() {
        super.onDestroy();
        this.flag = true;
        Log.e(TAG, "onDestroyed方法被调用!");
    }
    @Override
    public void onRebind(Intent intent) {
        Log.e(TAG, "onRebind方法被调用!");
        super.onRebind(intent);
    }
}
