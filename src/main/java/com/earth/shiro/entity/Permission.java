package com.earth.shiro.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "`permission`")
public class Permission {
    @Id
    @Column(name = "`permission_id`")
    private Integer permissionId;

    @Column(name = "`permission_code`")
    private String permissionCode;

    @Column(name = "`permission_name`")
    private String permissionName;
    @Column(name = "`permission_url_pattern`")
    private String permissionUrlPattern;

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
     * @return permission_code
     */
    public String getPermissionCode() {
        return permissionCode;
    }

    /**
     * @param permissionCode
     */
    public void setPermissionCode(String permissionCode) {
        this.permissionCode = permissionCode;
    }

    /**
     * @return permission_name
     */
    public String getPermissionName() {
        return permissionName;
    }

    /**
     * @param permissionName
     */
    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public String getPermissionUrlPattern() {
        return permissionUrlPattern;
    }

    public void setPermissionUrlPattern(String permissionUrlPattern) {
        this.permissionUrlPattern = permissionUrlPattern;
    }
}
