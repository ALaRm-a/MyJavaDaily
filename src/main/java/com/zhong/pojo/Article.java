package com.zhong.pojo;

import com.zhong.anno.State;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.hibernate.validator.constraints.URL;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;

/**
 * 文章实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Validated
public class Article {
    private Integer id; // ID

    @NotEmpty(message = "文章标题不能为空")
    @Pattern(regexp = "^\\S{1,10}$")
    private String title; // 文章标题

    @NotEmpty(message = "文章内容不能为空")
    private String content; // 文章内容

    @NotEmpty
    @URL
    private String coverImg; // 文章封面

    @NotEmpty(message = "文章状态不能为空")
   // @Pattern(regexp = "^(已发布|草稿)$", message = "文章状态只能是'已发布'或'草稿'")
    @State
    private String state; // 文章状态：只能是[已发布] 或者 [草稿]

    @NotNull(message = "文章分类不能为空")
    private Integer categoryId; // 文章分类ID

    private Integer createUser; // 创建人ID
    private LocalDateTime createTime; // 创建时间
    private LocalDateTime updateTime; // 修改时间
}