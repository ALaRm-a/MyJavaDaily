package com.zhong.service;

import com.zhong.pojo.User;
import org.hibernate.validator.constraints.URL;

public interface UserService {
    User findNameById(String username);

    void AddUser(String username, String password);

    void updateUserInfo(User user);

    void updateUserAvatar(String avatar, Integer id);

    void updatePassword(String rePwd);
}
