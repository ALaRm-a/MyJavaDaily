package com.zhong.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

/**
 * 分类实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    private Integer id; // ID
    private String categoryName; // 分类名称
    private String categoryAlias; // 分类别名
    private Integer createUser; // 创建人ID
    private LocalDateTime createTime; // 创建时间
    private LocalDateTime updateTime; // 修改时间
}