package com.zhong.mapper;

import com.zhong.pojo.Category;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface CategoryMapper {
    
    /**
     * 插入分类
     * @param category 分类对象
     */
    @Insert("INSERT INTO category (category_name, category_alias, create_user, create_time, update_time) " +
            "VALUES (#{categoryName}, #{categoryAlias}, #{createUser}, now(), now())")
    void insert(Category category);

    /**
     * 根据用户ID查询分类列表
     * @param userId 用户ID
     * @return 分类列表
     */
    @Select("SELECT * FROM category WHERE create_user = #{userId} ORDER BY update_time DESC")
    List<Category> findByUserId(Integer userId);

    /**
     * 根据ID查询分类详情
     * @param id 分类ID
     * @return 分类对象
     */
    @Select("SELECT * FROM category WHERE id = #{id}")
    Category findById(Integer id);

    /**
     * 更新分类信息
     * @param category 分类对象
     */
    @Update("UPDATE category SET category_name = #{categoryName}, category_alias = #{categoryAlias}, " +
            "update_time = now() WHERE id = #{id}")
    void update(Category category);
}