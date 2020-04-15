package com.example.demo;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.IOException;

/**
 * @Author godv
 * Date on 2020/4/15  11:35
 */
public class SDActivity extends AppCompatActivity {
    private EditText editname;
    private EditText editdetail;
    private Button btnsave;
    private Button btnclean;
    private Button btnread;
    private Context mContext;

    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            "android.permission.READ_EXTERNAL_STORAGE",
            "android.permission.WRITE_EXTERNAL_STORAGE"};


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
        setContentView(R.layout.file_layout);
        //申请权限
        verifyStoragePermissions(this);
        mContext = getApplication();
        editdetail = (EditText) findViewById(R.id.editdetail);
        editname = (EditText) findViewById(R.id.editname);
        btnclean = (Button) findViewById(R.id.btnclean);
        btnsave = (Button) findViewById(R.id.btnsave);
        btnread = (Button) findViewById(R.id.btnread);
        //清空
        btnclean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editdetail.setText("");
                editname.setText("");
            }
        });
        //保存
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String filename = editname.getText().toString();
                String filedetail = editdetail.getText().toString();
                SDFileHelper sdHelper = new SDFileHelper(mContext);
                try {
                    sdHelper.savaFileToSD(filename, filedetail);
                    Toast.makeText(getApplicationContext(), "数据写入成功", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "数据写入失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String detail = "";
                SDFileHelper sdHelper2 = new SDFileHelper(mContext);
                try {
                    String filename2 = editname.getText().toString();
                    detail = sdHelper2.readFromSD(filename2);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Toast.makeText(getApplicationContext(), detail, Toast.LENGTH_SHORT).show();
            }
        });

    }
}
