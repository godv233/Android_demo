package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class News {
    private String title;
    private String content;
    private int icon;
}