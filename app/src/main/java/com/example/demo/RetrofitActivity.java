package com.example.demo;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androidnetworking.gsonparserfactory.GsonParserFactory;
import com.example.demo.adapter.ResultReAdapter;
import com.example.demo.entity.JsonResult;
import com.google.gson.Gson;
import java.io.IOException;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @Author godv
 * Date on 2020/4/27  23:27
 */
public class RetrofitActivity extends AppCompatActivity {
    private static final String TAG = "RetrofitActivity";
    private ResultReAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.retrofit_layout);
        initView();
    }

    private void initView() {
        RecyclerView recyclerView = findViewById(R.id.result_re_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ResultReAdapter();
        recyclerView.setAdapter(adapter);

    }

    //get请求
    public void retrofitGet(View view) {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://10.0.2.2:9102")
                .build();
        RetrofitHelper api = retrofit.create(RetrofitHelper.class);
        Call<JsonResult> task = api.getJson();

        //异步请求
        task.enqueue(new Callback<JsonResult>() {
            @Override
            public void onResponse(Call<JsonResult> call, Response<JsonResult> response) {
                JsonResult jsonResult = response.body();
                updateList(jsonResult);
            }

            @Override
            public void onFailure(Call<JsonResult> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });

    }

    //更新UI
    private void updateList(JsonResult resultObj) {
        adapter.setData(resultObj);
    }
}
