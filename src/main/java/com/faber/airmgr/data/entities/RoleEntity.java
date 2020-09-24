package com.faber.airmgr.data.entities;

public class RoleEntity {
    private Long id;
    private String nameRole;

    public RoleEntity() {
    }

    public RoleEntity(Long id, String nameRole) {
        this.id = id;
        this.nameRole = nameRole;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameRole() {
        return nameRole;
    }

    public void setNameRole(String nameRole) {
        this.nameRole = nameRole;
    }
    
}
