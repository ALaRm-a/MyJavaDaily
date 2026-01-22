package com.zhong.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

/**
 * 用户实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Integer id; // ID
    private String username; // 用户名
    private String password; // 密码
    private String nickname; // 昵称
    private String email; // 邮箱
    private String userPic; // 头像
    private LocalDateTime createTime; // 创建时间
    private LocalDateTime updateTime; // 修改时间
}