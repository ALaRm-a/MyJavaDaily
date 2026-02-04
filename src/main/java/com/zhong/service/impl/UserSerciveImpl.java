package com.zhong.service.impl;

import com.zhong.mapper.UserMapper;
import com.zhong.pojo.User;
import com.zhong.service.UserService;
import com.zhong.utils.MD5Util;
import com.zhong.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

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

    @Override
    public void updateUserAvatar(String avatar, Integer id) {
        userMapper.updateUserAvatar(avatar,id);
    }

    @Override
    public void updatePassword(String rePwd) {

        //需要获取ID和对密码加密

        Map<String,Object> userMap = ThreadLocalUtil.get();
        Integer id = (Integer) userMap.get("id");

        userMapper.updatePassword(MD5Util.encrypt(rePwd),id);
    }
}
