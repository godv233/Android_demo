package com.example.demo.net;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demo.R;
import com.example.demo.adapter.ResultAdapter.ResultAdapter;
import com.example.demo.entity.MomentItem;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 网络相关
 * @Author godv
 * Date on 2020/4/27  15:08
 */
public class NetActivity extends Activity {
    private static final String TAG = "MainActivity";
    public static final int WHAT_LOADER_RESULT = 1;
    private static Handler mHandler;
    private RecyclerView mResultList;
    private ResultAdapter mResultAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.load_json);
        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch(msg.what) {
                    case WHAT_LOADER_RESULT:
                        MomentItem result = (MomentItem) msg.obj;
                        refreshResultList(result);
                        break;
                }
            }
        };
        initView();
    }
    private void initView() {
        mResultList = findViewById(R.id.result_list);
        mResultList.setLayoutManager(new LinearLayoutManager(this));
        mResultAdapter = new ResultAdapter();
        mResultList.setAdapter(mResultAdapter);
    }
    private void refreshResultList(MomentItem result) {
        Log.d(TAG,"refreshResultList -- ");
        mResultAdapter.setData(result);
    }

    //点击事件
    public void startRequest(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                loadData();
            }
        }).start();
    }
    //从服务端获取数据
    private void loadData() {
        try {
            URL url = new URL("https://www.sunofbeach.net/content/content/moment/list/1153952789488054272/1");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            int responseCode = httpURLConnection.getResponseCode();
            if(responseCode == 200) {
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
                String line = bufferedReader.readLine();
                Log.d(TAG,"line -- > " + line);
                bufferedReader.close();
                Message message = mHandler.obtainMessage();
                message.what = WHAT_LOADER_RESULT;
                Gson gson = new Gson();
                message.obj = gson.fromJson(line, MomentItem.class);
                mHandler.sendMessage(message);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
