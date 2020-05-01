package com.example.demo.picasso;

import android.os.Bundle;
import android.os.Environment;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.demo.R;
import com.squareup.picasso.Picasso;

import java.io.File;


/**
 * @Author godv
 * Date on 2020/5/1  9:24
 */
public class PicassoActivity extends AppCompatActivity {
    private ImageView imageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.picasso_layout);
        imageView = findViewById(R.id.image_picasso);
        //加载网络图片
//        Picasso.with(this).load("http://guolin.tech/book.png").into(imageView);
        //加载资源图片
//        Picasso.with(this).load(R.mipmap.header).into(imageView);
        //加载本地图片 路径前需要前缀"file://"
//        String path = this.getExternalFilesDir(Environment.DIRECTORY_PICTURES) + File.separator + "15.png";
//        Picasso.with(this).load("file://" +path).into(imageView);
        //图片裁剪
//        Picasso.with(this).load("http://guolin.tech/book.png")
//                .resize(800,800)
//                .into(imageView);
        //图片旋转
//        Picasso.with(this).load("http://guolin.tech/book.png")
//                .rotate(180)
//                .into(imageView);
        //图片转换 自定义图片效果
        Picasso.with(this).load("http://guolin.tech/book.png")
                .resize(800,1000)
                .centerCrop()
                .transform(new BlurTransformation(this))
                .into(imageView);

    }


}
