package com.example.demo;
/**
 * 适配器
 */

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AdapterActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adapter);
        //adapterView V
        ListView listView=findViewById(R.id.listView);
        //数据源。一般从服务端获取
        String[] strings={"a","b","c","d","e","f","g","h","i","j","k","l","q","w","w","w"};
        //controller
        ArrayAdapter<String> adapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,strings);
        //listView设置适配器
        listView.setAdapter(adapter);
    }
}
