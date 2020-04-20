package com.example.demo;

import android.app.Activity;
import android.os.Bundle;
import android.se.omapi.Reader;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;


/**
 * @Author godv
 * Date on 2020/4/20  22:05
 */
public class HttpActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.http_layout);
        Button btn = findViewById(R.id.btnHttp);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread() {
                    @Override
                    public void run() {
                        //1.使用HttpURLConnection
                        try {
                            URL url = new URL("http://10.0.2.2:80");
                            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                            urlConnection.setConnectTimeout(10000);
                            urlConnection.setRequestMethod("GET");
                            urlConnection.setRequestProperty("Accept", "*/*");
                            urlConnection.connect();
                            if (urlConnection.getResponseCode() == 200) {
                                InputStream inputStream = urlConnection.getInputStream();
                                BufferedReader reader=new BufferedReader(new InputStreamReader(inputStream));
                                String line=reader.readLine();
                                Log.d("net",line);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        //2.使用HttpClient被谷歌弃用了。
                    }
                }.start();
            }
        });
    }
}
