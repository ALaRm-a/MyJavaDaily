package com.zhong.controller;

import com.zhong.pojo.Category;
import com.zhong.service.CategoryService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@Validated
@Slf4j
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * 添加文章分类
     * @param category 分类对象，包含categoryName和categoryAlias参数
     * @return 操作结果
     */
    @PostMapping
    public Result addCategory(@RequestBody @Validated(Category.add.class) Category category) {
        log.info("添加文章分类: {}", category);
        
        try {
            categoryService.addCategory(category);
            log.info("文章分类添加成功");
            return Result.success();
        } catch (Exception e) {
            log.error("文章分类添加失败: {}", e.getMessage());
            return Result.error("添加失败");
        }
    }

    /**
     * 获取当前用户的文章分类列表
     * @return 分类列表（JSON格式）
     */
    @GetMapping
    public Result<List<Category>> getCategoryList() {
        log.info("获取文章分类列表");

        try {
            List<Category> categoryList = categoryService.getCategoryList();
            log.info("成功获取 {} 个文章分类", categoryList.size());
            return Result.success(categoryList);
        } catch (Exception e) {
            log.error("获取文章分类列表失败: {}", e.getMessage());
            return Result.error("获取失败");
        }
    }

    /**
     * 根据ID获取分类详情
     * @param id 分类ID
     * @return 分类详情（JSON格式）
     */
    @GetMapping("/detail/{id}")
    public Result<Category> getCategoryDetail(@PathVariable Integer id) {
        log.info("获取分类详情，ID: {}", id);

        try {
            Category category = categoryService.getCategoryDetail(id);
            log.info("成功获取分类详情: {}", category);
            return Result.success(category);
        } catch (Exception e) {
            log.error("获取分类详情失败: {}", e.getMessage());
            return Result.error(e.getMessage());
        }
    }

    /**
     * 更新分类信息
     * @param category 分类对象，包含id、categoryName和categoryAlias参数
     * @return 操作结果
     */
    @PutMapping
    public Result updateCategory(@RequestBody @Validated(Category.update.class) Category category) {
        log.info("更新文章分类: {}", category);

        try {
            categoryService.updateCategory(category);
            log.info("文章分类更新成功");
            return Result.success();
        } catch (Exception e) {
            log.error("文章分类更新失败: {}", e.getMessage());
            return Result.error(e.getMessage());
        }
    }
}
