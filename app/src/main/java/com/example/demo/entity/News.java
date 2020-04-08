package com.example.demo.entity;


public class News {
    private String title;
    private String content;
    private int icon;

    public News(String title, String content, int icon) {
        this.title = title;
        this.content = content;
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public int getIcon() {
        return icon;
    }
}