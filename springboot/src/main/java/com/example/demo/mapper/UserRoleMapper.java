package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.UserRole;
import org.apache.ibatis.annotations.Select;

/**
 * 功能描述:用户角色数据层
 * @Author: Liu Heng
*/
public interface UserRoleMapper extends BaseMapper<UserRole> {

    @Select("delete from user_role where user_id=#{userId}")
    void deleteByUserRoleId(Long userId);
}
