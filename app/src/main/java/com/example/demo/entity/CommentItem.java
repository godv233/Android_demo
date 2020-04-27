package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author godv
 * Date on 2020/4/27  20:27
 */
@Data
@AllArgsConstructor
public class CommentItem {
    private int articleId;
    private String CommentContent;
}
