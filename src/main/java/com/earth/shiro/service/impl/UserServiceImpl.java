package com.earth.shiro.service.impl;

import com.earth.shiro.entity.Dto.UserDetailDto;
import com.earth.shiro.mapper.UserMapper;
import com.earth.shiro.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    @Transactional
    public void checkLogin(String userName, String passWord , boolean rememberMe) throws Exception {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(userName, passWord);
        usernamePasswordToken.setRememberMe(rememberMe);
        subject.login(usernamePasswordToken);
    }

    @Override
    public UserDetailDto queryUserRoleAndPermissionByUserName(String username) {
        UserDetailDto userDetailDto = userMapper.queryUserRoleAndPermissionByUserName(username);
        return userDetailDto;
    }
}
