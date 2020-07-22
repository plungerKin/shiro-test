package com.earth.shiro.config;

import com.earth.shiro.entity.Dto.UserDetailDto;
import com.earth.shiro.entity.Permission;
import com.earth.shiro.entity.Role;
import com.earth.shiro.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class MyRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Override
    public String getName() {
        return "myRealm";
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return super.supports(token);
    }

    /**
     * 获取授权信息(查询当前用户的角色和权限信息)
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        // 获取当前用户的用户名
        /**
         * PrincipalCollection 内的信息是通过doGetAuthenticationInfo方法返回的token
         * token内包含什么信息PrincipalCollection就是含有哪一类的信息
         * 如果SimpleAuthenticationInfo中包含的是User这个对象 , 获取到的就是User对象
         * */
        UserDetailDto user = (UserDetailDto)principals.iterator().next();
//        UserDetailDto user = userService.queryUserRoleAndPermissionByUserName(username);
        Set<String> roles = user.getRoles().stream().map(Role::getRoleName).collect(Collectors.toSet());
        Set<String> permissions = user.getPermissions().stream().map(Permission::getPermissionCode).collect(Collectors.toSet());
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.setRoles(roles);
        simpleAuthorizationInfo.setStringPermissions(permissions);
        return simpleAuthorizationInfo;
    }

    /**
     * 获取认证的安全信息(安全即从数据库查询的数据)
     * @param auth
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) throws AuthenticationException {
        // AuthenticationToken 是传递 subject.login(token);
        UsernamePasswordToken token = (UsernamePasswordToken) auth;
        String username = token.getUsername();
        UserDetailDto user = userService.queryUserRoleAndPermissionByUserName(username);

        if(Objects.isNull(user)){
            return null;
        } else {
            SimpleAuthenticationInfo tokenInfo = new SimpleAuthenticationInfo(user, user.getPassword(), getName());
            return tokenInfo;
        }

    }
}
