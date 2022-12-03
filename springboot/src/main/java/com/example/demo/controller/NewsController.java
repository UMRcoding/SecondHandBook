package com.example.demo.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.entity.News;
import com.example.demo.mapper.NewsMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

@RestController
@RequestMapping("/news")
public class NewsController extends BaseController {

    @Resource
    NewsMapper newsMapper;

    /**
    * 功能描述:
    * @Param: [com.example.demo.entity.News]
    * @Author: Liu Heng
    * @return: com.example.demo.common.Result<?>
    */
    @PostMapping
    public Result<?> save(@RequestBody News news) {
        news.setTime(new Date());
        newsMapper.insert(news);
        return Result.success();
    }

    /**
    * 功能描述:
    * @Param: [com.example.demo.entity.News]
    * @Author: Liu Heng
    * @return: com.example.demo.common.Result<?>
    */
    @PutMapping
    public Result<?> update(@RequestBody News news) {
        newsMapper.updateById(news);
        return Result.success();
    }

    /**
    * 功能描述:
    * @Param: [java.lang.Long]
    * @Author: Liu Heng
    * @return: com.example.demo.common.Result<?>
    */
    @DeleteMapping("/{id}")
    public Result<?> update(@PathVariable Long id) {
        newsMapper.deleteById(id);
        return Result.success();
    }

    /**
    * 功能描述:
    * @Param: [java.lang.Long]
    * @Author: Liu Heng
    * @return: com.example.demo.common.Result<?>
    */
    @GetMapping("/{id}")
    public Result<?> getById(@PathVariable Long id) {
        return Result.success(newsMapper.selectById(id));
    }

    /**
    * 功能描述:
    * @Param: [java.lang.Integer, java.lang.Integer, java.lang.String]
    * @Author: Liu Heng
    * @return: com.example.demo.common.Result<?>
    */
    @GetMapping
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search) {
        LambdaQueryWrapper<News> wrapper = Wrappers.<News>lambdaQuery();
        if (StrUtil.isNotBlank(search)) {
            wrapper.like(News::getTitle, search);
        }
        Page<News> newsPage = newsMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
        return Result.success(newsPage);
    }
}
