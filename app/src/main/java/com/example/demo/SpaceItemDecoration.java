package com.example.demo;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @Author godv
 * Date on 2020/4/13  22:39
 * 提供Item间隔
 */
public class SpaceItemDecoration extends RecyclerView.ItemDecoration {
    private int spaceVal;
    public SpaceItemDecoration(int space){
        spaceVal=space;
    }
    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.left=spaceVal;
        outRect.right=spaceVal;
        outRect.top=spaceVal;
        outRect.bottom=spaceVal;

    }
}
