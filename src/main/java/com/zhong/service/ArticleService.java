package com.zhong.service;

import com.zhong.pojo.Article;
import com.zhong.pojo.PageBean;

public interface ArticleService {
    
    /**
     * 添加文章
     * @param article 文章对象
     */
    void addArticle(Article article);

    PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state);
}