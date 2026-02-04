package com.zhong.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zhong.mapper.ArticleMapper;
import com.zhong.pojo.Article;
import com.zhong.pojo.PageBean;
import com.zhong.service.ArticleService;
import com.zhong.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public void addArticle(Article article) {
        // 从ThreadLocal中获取用户信息
        Map<String, Object> claims = ThreadLocalUtil.get();
        Integer userId = (Integer) claims.get("id");
        
        // 设置创建用户ID
        article.setCreateUser(userId);
        
        // createTime和updateTime已经在Mapper的SQL语句中设置为now()
        // 调用Mapper插入数据
        articleMapper.insert(article);
    }

    @Override
    public PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state) {
        // 创建一个pagebenan对象
        PageBean<Article> pageBean = new PageBean<>();

        // 调用pagehelper方法，获取分页数据
        PageHelper.startPage(pageNum, pageSize);

        // 获取id值先
        Map<String, Object> claims = ThreadLocalUtil.get();
        Integer userId = (Integer) claims.get("id");

        List<Article> la = articleMapper.list(userId, categoryId, state);

        // 强转成对应的类型，调用对应的方法，pagehelper提供了方法获取总记录数和当前页数据
        Page<Article> pa = (Page<Article>) la;

        // 把数据填充到里面
        pageBean.setTotal(pa.getTotal());
        // set方法的变量要大写（默认提供的方法）
        pageBean.setPages(pa.getResult());

        return pageBean;
    }
}