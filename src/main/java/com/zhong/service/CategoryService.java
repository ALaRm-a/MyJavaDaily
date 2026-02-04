package com.zhong.service;

import com.zhong.pojo.Category;

import java.util.List;

public interface CategoryService {
    
    /**
     * 添加分类
     * @param category 分类对象
     */
    void addCategory(Category category);

    /**
     * 获取当前用户的分类列表
     * @return 分类列表
     */
    List<Category> getCategoryList();

    /**
     * 根据ID获取分类详情
     * @param id 分类ID
     * @return 分类对象
     */
    Category getCategoryDetail(Integer id);

    /**
     * 更新分类信息
     * @param category 分类对象
     */
    void updateCategory(Category category);
}