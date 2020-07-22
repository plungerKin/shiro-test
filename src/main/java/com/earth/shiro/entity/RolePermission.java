package com.earth.shiro.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "`role_permission`")
public class RolePermission {
    @Id
    @Column(name = "`role_permission_id`")
    private Integer rolePermissionId;

    @Column(name = "`permission_id`")
    private Integer permissionId;

    @Column(name = "`role_id`")
    private Integer roleId;

    /**
     * @return role_permission_id
     */
    public Integer getRolePermissionId() {
        return rolePermissionId;
    }

    /**
     * @param rolePermissionId
     */
    public void setRolePermissionId(Integer rolePermissionId) {
        this.rolePermissionId = rolePermissionId;
    }

    /**
     * @return permission_id
     */
    public Integer getPermissionId() {
        return permissionId;
    }

    /**
     * @param permissionId
     */
    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }

    /**
     * @return role_id
     */
    public Integer getRoleId() {
        return roleId;
    }

    /**
     * @param roleId
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}
