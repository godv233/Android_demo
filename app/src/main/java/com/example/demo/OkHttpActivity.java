package com.example.demo;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.demo.entity.CommentItem;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import kotlin.Pair;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.BufferedSink;

/**
 * @Author godv
 * Date on 2020/4/27  19:51
 */
public class OkHttpActivity extends AppCompatActivity {
    private static final String BASE_URL = "http://10.0.2.2:9102";
    private static final String TAG = "OkHttpActivity";

    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            "android.permission.READ_EXTERNAL_STORAGE",
            "android.permission.WRITE_EXTERNAL_STORAGE"};

    /*
     * android 动态权限申请
     * */
    public static void verifyStoragePermissions(Activity activity) {
        try {
            //检测是否有写的权限
            int permission = ActivityCompat.checkSelfPermission(activity,
                    "android.permission.WRITE_EXTERNAL_STORAGE");
            if (permission != PackageManager.PERMISSION_GRANTED) {
                // 没有写的权限，去申请写的权限，会弹出对话框
                ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.okhttp_layout);
        verifyStoragePermissions(this);
    }

    //get请求数据
    public void getRequest(View view) {
        //okHttp的客户端
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .connectTimeout(10000, TimeUnit.MILLISECONDS)
                .build();
        //请求内容
        Request request = new Request.Builder()
                .get()
                .url(BASE_URL + "/get/text")
                .build();
        //创建请求任务
        final Call task = httpClient.newCall(request);
        //异步请求
        task.enqueue(new Callback() {
            //失败
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.e(TAG, "onFailure--->" + e.toString());
            }

            //响应
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                int code = response.code();
                Log.e(TAG, "code--->" + code);
                Log.e(TAG, "body--->" + response.body().string());
            }
        });


    }

    //post提交数据
    public void postRequest(View view) {
        //okHttp的客户端
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .connectTimeout(10000, TimeUnit.MILLISECONDS)
                .build();
        //请求数据
        CommentItem item = new CommentItem(234134123, "不会吧 啊sir");
        Gson gson = new Gson();
        String json = gson.toJson(item);
        //body请求体
        RequestBody requestBody = RequestBody.create(json, MediaType.parse("application/json"));
        //创建请求
        Request request = new Request.Builder()
                .post(requestBody)
                .url(BASE_URL + "/post/comment")
                .build();
        Call task = httpClient.newCall(request);
        task.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.e(TAG, "onFailure--->" + e.toString());
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                int code = response.code();
                Log.e(TAG, "code--->" + code);
                if (response.body() != null) {
                    Log.e(TAG, "body--->" + response.body().string());
                }

            }
        });
    }

    //文件上传 （单文件和多文件）
    public void upload(View view) {
        //okHttp的客户端
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .connectTimeout(10000, TimeUnit.MILLISECONDS)
                .build();
        File file1 = new File("/storage/emulated/0/crash-2020-04-27-10-01-21-1587981681535.log");
        File file2 = new File("/storage/emulated/0/crash-2020-04-27-08-43-05-1587976985284.log");
        RequestBody fileBody1 = RequestBody.create(file1, MediaType.parse("text/plain"));
        RequestBody fileBody2 = RequestBody.create(file2, MediaType.parse("text/plain"));
        RequestBody body = new MultipartBody.Builder()
                .addFormDataPart("files", file1.getName(), fileBody1)
                .addFormDataPart("files", file2.getName(), fileBody2)
                .build();

        //请求
        Request request = new Request.Builder()
                .post(body)
                .url(BASE_URL + "/files/upload")
                .build();
        Call task = httpClient.newCall(request);
        task.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.e(TAG, "onFailure--->" + e.toString());
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                int code = response.code();
                Log.e(TAG, "code--->" + code);
                if (response.body() != null) {
                    Log.e(TAG, "body--->" + response.body().string());
                }
            }
        });
    }

    //文件下载
    public void download(View view) {
        //okHttp的客户端
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .connectTimeout(10000, TimeUnit.MILLISECONDS)
                .build();
        Request request = new Request.Builder()
                .url(BASE_URL + "/download/15")
                .build();
        //同步
        final Call task = httpClient.newCall(request);
        new Thread() {
            @Override
            public void run() {
                InputStream inputStream = null;
                FileOutputStream outputStream = null;
                try {
                    Response execute = task.execute();
                    Headers headers = execute.headers();
                    for (int i = 0; i < headers.size(); i++) {
                        String key = headers.name(i);
                        String value = headers.value(i);
                        Log.e(TAG, key + "===" + value);
                    }
                    String type = headers.get("Content-Type");
                    String fileName = headers.get("Content-disposition").replace("attachment; filename=", "");
                    File outFile = new File(OkHttpActivity.this.getExternalFilesDir(Environment.DIRECTORY_PICTURES) + File.separator + fileName);
                    if (!outFile.getParentFile().exists()) {
                        outFile.mkdirs();
                    }
                    if (!outFile.exists()) {
                        outFile.createNewFile();
                    }
                    inputStream = execute.body().byteStream();
                    outputStream = new FileOutputStream(outFile);
                    byte[] buffer = new byte[1024];
                    int len = 0;
                    while ((len = inputStream.read(buffer, 0, buffer.length)) != -1) {
                        outputStream.write(buffer, 0, len);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        inputStream.close();
                        outputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }
        }.start();
    }
}
