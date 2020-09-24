package com.faber.airmgr.data.entities;

public class UserEntity {
    private Long id;
    private String nameUser;
    private String password;

    public UserEntity() {
    }

    public UserEntity(Long id, String nameUser, String password) {
        this.id = id;
        this.nameUser = nameUser;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
    
}
