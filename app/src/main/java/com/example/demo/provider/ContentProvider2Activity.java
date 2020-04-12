package com.example.demo.provider;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.demo.R;


public class ContentProvider2Activity extends AppCompatActivity {
    private Button btninsert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cp_layout2);
        btninsert = (Button) findViewById(R.id.btninsert);
        //读取contentprovider 数据
        final ContentResolver resolver = this.getContentResolver();
        btninsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues values = new ContentValues();
                values.put("name", "测试");
                Uri uri = Uri.parse("content://com.example.demo.myProvider/test");
                resolver.insert(uri, values);
                Toast.makeText(getApplicationContext(), "数据插入成功", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
