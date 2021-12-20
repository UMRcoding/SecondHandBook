package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@TableName("news")
@Data
public class News {
    /**
     * 功能描述: ID
     * @Author: Liu Heng
    */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 功能描述:标题
     * @Author: Liu Heng
    */
    private String title;

    /**
     * 功能描述: 内容
     * @Author: Liu Heng
    */
    private String content;

    /**
     * 功能描述: 作者
     * @Author: Liu Heng
    */
    private String author;

    /**
     * 功能描述: 发布时间
     * @Author: Liu Heng
    */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date time;
}
