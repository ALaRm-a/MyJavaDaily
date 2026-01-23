package com.zhong.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;


import java.time.LocalDateTime;

/**
 * 用户实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Validated
public class User {
    @NotNull
    private Integer id; // ID
    private String username; // 用户名
    @JsonIgnore // 于password的话不能返回到前台，加上@jsonignore注解，在返回json格式的数据的时候就不会返回这个数据了
    private String password; // 密码

    @NotEmpty
    @Pattern(regexp = "^\\S{1,10}$")
    private String nickname; // 昵称

    @NotEmpty
    @Email
    private String email; // 邮箱
    private String userPic; // 头像
    private LocalDateTime createTime; // 创建时间
    private LocalDateTime updateTime; // 修改时间
}