package com.zhong.controller;

import com.zhong.pojo.Article;
import com.zhong.pojo.PageBean;
import com.zhong.service.ArticleService;
import com.zhong.utils.OSSUtils;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/article")
@Validated
@Slf4j
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @GetMapping("/list")
    public Result list(){
        return Result.success("所有文章信息");
    }

    /**
     * 添加文章
     * @param article 文章对象
     * @return 操作结果
     */
    @PostMapping("/json")
    public Result addArticle(@RequestBody @Validated Article article) {
        log.info("添加文章: {}", article);

        try {
            articleService.addArticle(article);
            log.info("文章添加成功");
            return Result.success();
        } catch (Exception e) {
            log.error("文章添加失败: {}", e.getMessage());
            return Result.error("添加失败");
        }
    }

    @GetMapping("/selectlist")
    public Result<PageBean<Article>> selectlist(
            Integer pageNum,
            Integer pageSize,
            @RequestParam(required = false) Integer categoryId,
            @RequestParam(required = false) String state
    ){
        log.info("获取文章指定页数的列表");

        // 返回的是一个集合
        PageBean<Article> pb = articleService.list(pageNum,pageSize,categoryId,state);
        return Result.success(pb);
    }

    @PostMapping("/upload")
    public  Result<String> file(MultipartFile upfile) throws Exception {
        log.info("上传文件: {}", upfile);
        // 获取上传的文件名
        String originalFilename = upfile.getOriginalFilename();
        // 生成新的文件名
        String s = UUID.randomUUID().toString() + originalFilename.substring(originalFilename.lastIndexOf("."));
        //upfile.transferTo(new File("C:\\Users\\zhong\\Desktop\\" + s));

        // 调用上传方法
        String url = OSSUtils.uploadFile(s, upfile.getInputStream());

        return Result.success(url);
    }

}
