package com.example.demo.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.demo.entity.Anime;

import java.util.List;

/**
 * @Author godv
 * Date on 2020/4/21  10:30
 */
@Dao
public interface AnimeDao {

    @Query("SELECT * FROM  anime")
    List<Anime> getAllAnime(); //加载所有动漫数据

    @Query("SELECT * FROM anime WHERE playDate = :name")
    Anime loadAnimeByName(String name); //根据名字加载动漫

    @Insert
    void insertOneAnime(Anime anime); //插入一条动漫信息

    @Insert
    void insertMultiAnimes(Anime... animes); //插入多条动漫信息

    @Update(onConflict = OnConflictStrategy.REPLACE)
    int updateUsers(Anime... animes); //更新动漫信息，当有冲突时则进行替代

    @Delete
    void deleteAnime(Anime anime);
}

