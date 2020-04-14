package com.example.demo.view;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.view.View;

import com.example.demo.R;

/**
 * @Author godv
 * Date on 2020/4/14  15:21
 */
public class PaintView extends View {
    private Paint mPaint;

    public PaintView(Context context) {
        super(context);
        init();
    }

    /**
     * 设置画笔属性
     */
    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);          //抗锯齿
        mPaint.setColor(getResources().getColor(R.color.colorPrimaryDark));//画笔颜色
        mPaint.setStyle(Paint.Style.FILL);  //画笔风格
        mPaint.setTextSize(36);             //绘制文字大小，单位px
        mPaint.setStrokeWidth(5);           //画笔粗细
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(getResources().getColor(R.color.colorPrimary));   //设置画布背景颜色
//        canvas.drawCircle(200, 200, 100, mPaint);           //画实心圆
//        canvas.drawRect(0, 0, 200, 100, mPaint);            //画矩形
        canvas.drawBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.header), 0, 0, mPaint);//绘制bitmap
//        Path path = new Path();
//        path.moveTo(10, 10); //移动到 坐标10,10
//        path.lineTo(100, 50);
//        path.lineTo(200,40);
//        path.lineTo(300, 20);
//        path.lineTo(200, 10);
//        path.lineTo(100, 70);
//        path.lineTo(50, 40);
//        path.close();
//        canvas.drawPath(path,mPaint);
//        canvas.drawText("godv2333~",50,50,mPaint);    //绘制文字
//
    }
}
