package com.example.demo;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;

/**
 * @Author godv
 * Date on 2020/4/27  17:51
 */
public class BitMapActivity extends Activity {
    private String TAG="bitmap";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.load_img);

    }

    public void requestImage(View view) {
        ImageView imageView = findViewById(R.id.image_result);

        BitmapFactory.Options options=new BitmapFactory.Options();
        //固定值
//        options.inSampleSize=10;
        //动态计算option中inSampleSize值的大小，也就是图片的采样率
        //设置为true以后呢，不是真的载入到内存中，只是获取到图片的相关信息
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), R.mipmap.test,options);
        int width = options.outWidth;
        int height = options.outHeight;
        int measuredWidth = imageView.getMeasuredWidth();
        int measuredHeight = imageView.getMeasuredHeight();
        Log.d(TAG,"width -- > " + width + " measure width -- > " + measuredWidth);
        Log.d(TAG,"height -- > " + height + " measure height -- > " + measuredHeight);
        int sampleSize;
        if(width < measuredWidth || height < measuredHeight) {
            sampleSize = 1;
        } else {
            int scaleX = width / measuredWidth;
            int scaleY = height / measuredHeight;
            sampleSize = scaleX > scaleY ? scaleX : scaleY;
        }
        Log.d(TAG,"sampleSize -- > " + sampleSize);
        options.inSampleSize = sampleSize;
        //要变成false了，因为真的要载入到内存中了
        options.inJustDecodeBounds = false;
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.test,options);
        imageView.setImageBitmap(bitmap);

    }
}
