package com.zhong.mapper;

import com.zhong.pojo.Article;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ArticleMapper {
    
    /**
     * 插入文章
     * @param article 文章对象
     */
    @Insert("INSERT INTO article (title, content, cover_img, state, category_id, create_user, create_time, update_time) " +
            "VALUES (#{title}, #{content}, #{coverImg}, #{state}, #{categoryId}, #{createUser}, now(), now())")
    void insert(Article article);

    List<Article> list(Integer userId, Integer categoryId, String state);
}