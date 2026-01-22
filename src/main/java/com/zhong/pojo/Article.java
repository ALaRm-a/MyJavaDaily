package com.zhong.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

/**
 * 文章实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Article {
    private Integer id; // ID
    private String title; // 文章标题
    private String content; // 文章内容
    private String coverImg; // 文章封面
    private String state; // 文章状态：只能是[已发布] 或者 [草稿]
    private Integer categoryId; // 文章分类ID
    private Integer createUser; // 创建人ID
    private LocalDateTime createTime; // 创建时间
    private LocalDateTime updateTime; // 修改时间
}