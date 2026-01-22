package com.zhong.controller;

import com.zhong.pojo.User;
import com.zhong.service.UserService;
import com.zhong.utils.JWTUtils;
import com.zhong.utils.MD5Util;
import jakarta.validation.constraints.Pattern;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/user")
@Validated
@Slf4j
public class UserController {

    @Autowired
    public UserService userService;


    @PostMapping("/register")
    public Result UerRegister(@Pattern(regexp = "^\\S{5,16}$") String username, @Pattern(regexp = "^\\S{5,16}$") String password){

        log.info("用户注册");
        // 非空判断
        User u = userService.findNameById(username);
        if(u!=null){
            log.info("用户名被占用");
            // 非空，名字已经被占用
            return Result.error();
        }else{
            log.info("用户名可用");
            userService.AddUser(username,password);
            return Result.success();
        }
    }

    @PostMapping("/login")
    public Result UerLogin(@Pattern(regexp = "^\\S{5,16}$") String username, @Pattern(regexp = "^\\S{5,16}$") String password){

        log.info("用户登录");
     // 用户是否存在判断

        User loginUser = userService.findNameById(username);
        if(loginUser==null){
            log.info("用户不存在");
            return Result.error("用户不存在");
        }

        // MD5加密检验
        // 先对传进来的密码进行加密，工具类里面要一次性传入两个MD5加密
        if(MD5Util.verify(loginUser.getPassword(),MD5Util.encrypt(password))){
            log.info("登录成功");

            // 返回JWT的密钥,使用ID和username作为加密的第二部分
            HashMap<String, Object> claims = new HashMap<>();
            claims.put("username",loginUser.getUsername());
            claims.put("id",loginUser.getId());
            String token = JWTUtils.genToken(claims);
            return Result.success(token);

        }else{
            log.info("密码错误,前后加密不一致");
            return Result.error("密码错误");
        }
    }




























        /*
    @PostMapping("/register")
    public Result UerRegister(@RequestBody User user){

        // 非空判断
        User u = userService.findNameById(user.getUsername());
        if(u!=null){
            // 非空，名字已经被占用
            return Result.error();
        }else{
            userService.AddUser(user.getUsername(),user.getPassword());
            return Result.success();
        }
     */
}
