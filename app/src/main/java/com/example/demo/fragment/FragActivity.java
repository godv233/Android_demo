package com.example.demo.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.demo.R;

/**
 * @Author godv
 * Date on 2020/4/12  17:53
 */
public class FragActivity  extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frg_layout);
        //动态加载
        MyFragment myFragment=new MyFragment();
        //数据交互
        Bundle b = new Bundle();
        b.putString("name", "jack");
        myFragment.setArguments(b);
        //事务
        FragmentManager sfm = getSupportFragmentManager();
        FragmentTransaction ft = sfm.beginTransaction();
        ft.add(R.id.rl_fragment,myFragment);
        ft.addToBackStack(null);
        ft.commit();

    }
}
