package com.zhong.mapper;

import com.zhong.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {

    @Select("select * from user where username=#{username}")
    User findNameById(String username);

    @Insert("insert into user (username,password,create_time,update_time) values (#{username},#{password},now(),now())")
    void AddUser(String username, String password);

    @Update("update user set nickname=#{nickname},email=#{email},update_time=#{updateTime} where id=#{id}")
    void updateUserInfo(User user);

    @Update("update user set user_pic=#{avatar},update_time=now() where id = #{id}")
    void updateUserAvatar(String avatar, Integer id);

    @Update("update user set password=#{rePassword},update_time=now() where id = #{id}")
    void updatePassword(String rePassword, Integer id);
}
