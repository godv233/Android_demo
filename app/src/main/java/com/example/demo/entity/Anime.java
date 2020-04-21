package com.example.demo.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author godv
 * Date on 2020/4/21  10:29
 */
@Entity(tableName = "anime")
@Data
@AllArgsConstructor
public class Anime {
    public Anime(){}
    /**
     * 动漫名
     * 如果是字符串做主键，记得加@NonNull，不然会报错
     */
    @NonNull
    @PrimaryKey(autoGenerate = true)
    private Long name;
    /**
     * 动漫类型
     */
    private String type;
    /**
     * 放送时间
     */
    private String playDate;
    /**
     * 集数
     */
    private int episode;
}

