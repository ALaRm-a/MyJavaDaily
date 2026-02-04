package com.zhong.pojo;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.Default;
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
    @NotNull(groups = update.class)
    private Integer id; // ID

    @NotEmpty(message = "分类名称不能为空")
    private String categoryName; // 分类名称

    @NotEmpty(message = "分类别名不能为空")
    private String categoryAlias; // 分类别名

    private Integer createUser; // 创建人ID
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime; // 创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime; // 修改时间

    public interface add extends Default {}
    public interface update extends Default{}
}