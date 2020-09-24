package com.faber.airmgr.data.entities;

import java.util.List;

public class UserRole {
    private long userId;
    private long roleId;
    
    private String roleName;
    private String userName;
    
    private List<RoleEntity> listRole ;
    private String password;

    public UserRole() {
    }

    public UserRole(long userId, long roleId, String roleName, String userName, List<RoleEntity> listRole, String password) {
        this.userId = userId;
        this.roleId = roleId;
        this.roleName = roleName;
        this.userName = userName;
        this.listRole = listRole;
        this.password = password;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<RoleEntity> getListRole() {
        return listRole;
    }

    public void setListRole(List<RoleEntity> listRole) {
        this.listRole = listRole;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
}
