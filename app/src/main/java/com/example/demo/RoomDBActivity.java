package com.example.demo;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.demo.db.AnimeDao;
import com.example.demo.db.AppDatabase;
import com.example.demo.entity.Anime;


/**
 * @Author godv
 * Date on 2020/4/21  9:55
 */
public class RoomDBActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btn_insert;
    private Button btn_query;
    private Button btn_update;
    private Button btn_delete;
    private AppDatabase appDB;
    private AnimeDao animeDao;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.db_layout);
        btn_insert = (Button) findViewById(R.id.btn_insert);
        btn_query = (Button) findViewById(R.id.btn_query);
        btn_update = (Button) findViewById(R.id.btn_update);
        btn_delete = (Button) findViewById(R.id.btn_delete);
        btn_query.setOnClickListener(this);
        btn_insert.setOnClickListener(this);
        btn_update.setOnClickListener(this);
        btn_delete.setOnClickListener(this);
        appDB = Room.databaseBuilder(this,AppDatabase.class,"anime.db")
                .addMigrations()
                .allowMainThreadQueries()
                .build();
        //拿到数据库操作对象
        animeDao = appDB.animeDao();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_insert:
                new Thread(){
                    @Override
                    public void run() {
                        Anime anime = new Anime();
                        anime.setPlayDate("动漫");
                        animeDao.insertOneAnime(anime);

                    }
                }.start();
                Toast.makeText(this, "插入成功", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_query:
                Anime anime = animeDao.loadAnimeByName("动漫");
                Toast.makeText(this, anime.toString(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_update:

                break;
            case R.id.btn_delete:

                break;
        }
    }
}
