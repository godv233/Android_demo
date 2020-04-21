package com.example.demo.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.demo.entity.Anime;

/**
 * @Author godv
 * Date on 2020/4/21  10:31
 */
@Database(entities = {Anime.class},version = 1,exportSchema = false)
 public abstract class AppDatabase extends RoomDatabase {
    public abstract AnimeDao animeDao();
}

