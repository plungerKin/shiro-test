package com.earth.shiro.service;

import com.earth.shiro.entity.Dto.UserDetailDto;

public interface UserService {
    void checkLogin(String userName, String passWord , boolean rememberMe) throws Exception ;

    UserDetailDto queryUserRoleAndPermissionByUserName(String username);

}
