package com.zhong.mapper;

import com.zhong.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Select("select * from user where username=#{username}")
    User findNameById(String username);

    @Insert("insert into user (username,password,create_time,update_time) values (#{username},#{password},now(),now())")
    void AddUser(String username, String password);
}
