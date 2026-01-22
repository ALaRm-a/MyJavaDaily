package com.zhong.service;

import com.zhong.pojo.User;

public interface UserService {
    User findNameById(String username);

    void AddUser(String username, String password);
}
