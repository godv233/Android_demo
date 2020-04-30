package com.example.demo.glide;

import android.os.Bundle;
import android.os.Environment;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.demo.R;

import java.io.File;

/**
 * @Author godv
 * Date on 2020/4/30  23:15
 */
public class GlideActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.glide_layout);
        //图片加载
        //加载网络图片
//        Glide.with(this).load("http://guolin.tech/book.png").into((ImageView) findViewById(R.id.image_glide));
        //加载资源图片
//        Glide.with(this).load(R.mipmap.header).into((ImageView) findViewById(R.id.image_glide));
        //加载本地图片
//        String path=this.getExternalFilesDir(Environment.DIRECTORY_PICTURES) + File.separator+"15.png";
//        Glide.with(this).load(path).into((ImageView) findViewById(R.id.image_glide));
        //加载网络gif
//        Glide.with(this).load("http://p3.pstatp.com/large/5b0000042eaffa033da6")
//                .into((ImageView) findViewById(R.id.image_glide));
        //加载资源gif
//        Glide.with(this).load(R.mipmap.link).into((ImageView) findViewById(R.id.image_glide));
        //加载缩略图 ，再加载原图 缩略图可以使用其他图片 也可以按比例缩放
        Glide.with(this).load("http://guolin.tech/book.png")
                .thumbnail(0.5f)
                .into((ImageView) findViewById(R.id.image_glide));

        //图片变换 多种api 使用RequestOptions来操作
        RequestOptions options = new RequestOptions().override(500,500);
        Glide.with(this).load("http://guolin.tech/book.png")
                .apply(options)
                .into((ImageView) findViewById(R.id.image_glide));
    }
}
