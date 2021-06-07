package com.melidh.desafiospring.domain.dto;

import com.melidh.desafiospring.domain.User;

public class BaseUserDTO {

    private Integer userId;
    private String userName;

    public BaseUserDTO(){

    }

    public BaseUserDTO(User user){
        this.userId = user.getId();
        this.userName = user.getUsername();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
