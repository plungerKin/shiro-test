package com.earth.shiro.mapper;

import com.earth.shiro.entity.User;
import com.earth.shiro.entity.Dto.UserDetailDto;
import com.earth.shiro.entity.UserExample;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {
    long countByExample(UserExample example);

    int deleteByExample(UserExample example);

    List<User> selectByExample(UserExample example);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    UserDetailDto queryUserRoleAndPermissionByUserName(@Param(value = "username") String username);
}
