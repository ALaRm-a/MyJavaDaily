package com.zhong.service.impl;

import com.zhong.mapper.UserMapper;
import com.zhong.pojo.User;
import com.zhong.service.UserService;
import com.zhong.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserSerciveImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Override
    public User findNameById(String username) {
        User user = userMapper.findNameById(username);
        return user;
    }

    @Override
    public void AddUser(String username, String password) {
        // 需要对密码进行加密
        password = MD5Util.encrypt(password);
        userMapper.AddUser(username,password);
    }

    @Override
    public void updateUserInfo(User user) {
        // 设置更新时间，其实这个放在SQL语句里面使用now()比较好
        user.setUpdateTime(LocalDateTime.now());
        userMapper.updateUserInfo(user);
    }
}
