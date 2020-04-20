package com.example.demo;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

/**
 * @Author godv
 * Date on 2020/4/14  22:13
 */
public class FileHelper {

    private Context mContext;

    public FileHelper() {
    }

    public FileHelper(Context mContext) {
        super();
        this.mContext = mContext;
    }

    /*
     * 文件存储保存：/data/data/<PackageName>/files
     * 这里定义的是一个文件保存的方法，写入到文件中，所以是输出流
     * */
    public void save(String filename, String fileContent) throws Exception {
        //这里我们使用私有模式,创建出来的文件只能被本应用访问,还会覆盖原文件哦
        FileOutputStream output = mContext.openFileOutput(filename, Context.MODE_PRIVATE);
        output.write(fileContent.getBytes());  //将String字符串以字节流的形式写入到输出流中
        output.close();         //关闭输出流
    }


    /*
     * 这里定义的是文件读取的方法
     * 默认存储路径：/data/data/<PackageName>/files
     * */
    public String read(String filename) throws IOException {
        //打开文件输入流
        FileInputStream input = mContext.openFileInput(filename);
        byte[] temp = new byte[1];
        StringBuilder sb = new StringBuilder("");
        int len = 0;
        //读取文件内容:
        while ((len = input.read(temp)) > 0) {
            sb.append(new String(temp, 0, len));
        }
        //关闭输入流
        input.close();
        return sb.toString();
    }

    /**
     * SharedPreferences:写入
     * /data/data/<PackageName>/shared_prefs
     */
    public void saveSharedPreferences(String filename, String fileContent){
        SharedPreferences.Editor editor=mContext.getSharedPreferences(filename,Context.MODE_PRIVATE).edit();
        editor.putString("data",fileContent);
        editor.commit();
    }
    /**
     * SharedPreferences:读取
     * /data/data/<PackageName>/shared_prefs
     */
    public String readSharedPreferences(String filename){
        SharedPreferences preferences = mContext.getSharedPreferences(filename, Context.MODE_PRIVATE);
        return preferences.getString("data","null");
    }

}
