package com.zhong.controller;

import com.zhong.pojo.User;
import com.zhong.service.UserService;
import com.zhong.utils.JWTUtils;
import com.zhong.utils.MD5Util;
import com.zhong.utils.ThreadLocalUtil;
import jakarta.validation.constraints.Pattern;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

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
        // 先对传进来的密码进行加密，工具类里面要一次性传入一个没有加密的明文密码，一个MD5加密
        if(loginUser.getPassword().equals(MD5Util.encrypt(password))){
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
    @GetMapping("/userinfo")
    public Result<User> getUserInfo(@RequestHeader(name = "Authorization") String token){

        // 获取token并解析得到username
        Map<String, Object> claims = JWTUtils.parseToken(token);
        String username = (String) claims.get("username");
        User user = userService.findNameById(username);
        return Result.success(user);
    }
*/

    @GetMapping("/userinfo")
    public Result<User> getUserInfo(){

        // 获取token并解析得到username

        Map<String, Object> claims = ThreadLocalUtil.get();
        String username = (String) claims.get("username");
        User user = userService.findNameById(username);

        log.info("用户信息获取成功");
        return Result.success(user);
    }

    @PutMapping("/update")
    public Result updateIonfo(@RequestBody @Validated User user){
        log.info("用户信息更新");
        userService.updateUserInfo(user);
        return Result.success();
    }

    @PatchMapping("/updateAvatar")
    public Result updateAvatar(@RequestParam   String avatar){
        // 没有准备静态的图片资源，先不做url的参数校验@URL

        log.info("用户头像更新");
        // 获取用户的ID
        Map<String, Object> claims = ThreadLocalUtil.get();
        Integer id  = (Integer) claims.get("id");
        userService.updateUserAvatar(avatar,id);
        return Result.success();
    }

    @PatchMapping("/updatePassword")
    public Result updatePassword(@RequestBody Map<String,Object> map){

        log.info("用户更新密码");

        // 获取到三个数据 先
        String oldPwd = (String) map.get("old_pwd");
        String newPwd = (String) map.get("new_pwd");
        String rePwd = (String) map.get("re_pwd");

        //对密码进行非空判断,直接取反，在为false的时候判断条件成立，直接返回错误信息

        if(!StringUtils.hasLength(oldPwd)||!StringUtils.hasLength(newPwd)||!StringUtils.hasLength(rePwd)){
            return Result.error("密码不能为空");
        }

        // 两次输入的旧密码是否一致，比较加密以后的数据
        Map<String,Object> userMap = ThreadLocalUtil.get();
        String username = (String) userMap.get("username");
        User user = userService.findNameById(username);
        if(!MD5Util.verify(oldPwd,user.getPassword())){

            // 为什么user直接得到的密码没有加密
            // 最开始的注册的还没有加入加密功能
            return Result.error("旧密码输入错误");
        }

        // 验证新密码和重新输入密码是否一致
        if(!newPwd.equals(rePwd)){
            return Result.error("两次输入的密码不一致");
        }
        userService.updatePassword(rePwd);

        log.info("用户密码更新成功");
        return Result.success();


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
