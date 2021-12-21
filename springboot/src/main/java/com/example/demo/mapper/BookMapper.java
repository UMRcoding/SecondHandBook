package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.Book;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BookMapper extends BaseMapper<Book> {
    /**
     * 功能描述: 根据用户id查询图书信息
     * @param userId
     * @Author: Liu Heng
    */
    List<Book> findByUserId(@Param("userId") Integer userId);
}
