package com.earth.shiro.entity.Dto;

import com.earth.shiro.entity.Permission;
import com.earth.shiro.entity.Role;
import com.earth.shiro.entity.User;
import lombok.Data;

import java.util.List;

@Data
public class UserDetailDto extends User {
    private List<Role> roles;
    private List<Permission> permissions;
}
