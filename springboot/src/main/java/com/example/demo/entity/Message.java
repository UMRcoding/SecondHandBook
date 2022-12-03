package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;


@Data
@TableName("message")
public class Message extends Model<Message> {
    /**
      * 功能描述:
      * @Param: 主键
      * @Author: Liu Heng
      * @return:
    */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 功能描述: 内容
     * @Param:
     * @Author: Liu Heng
     * @return:
    */
    private String content;

    /**
     * 功能描述: 评论人
     * @Param:
     * @Author: Liu Heng
     * @return:
    */
    private String username;

    /**
     * 功能描述: 评论时间
     * @Param:
     * @Author: Liu Heng
     * @return:
    */
    private String time;

    /**
     * 功能描述: 父ID
     * @Param:
     * @Author: Liu Heng
     * @return:
    */
    private Long parentId;

    @TableField(exist = false)
    private Message parentMessage;

    /**
     * 功能描述: 关联id
     * @Param:
     * @Author: Liu Heng
     * @return:
    */
    private Long foreignId;

    @TableField(exist = false)
    private String avatar;


}
