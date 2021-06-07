package com.mercadolivre.socialmeli.dto;

import com.mercadolivre.socialmeli.entities.User;

public class UserDTO {
    private Integer userId;
    private String name;

    public UserDTO(User user){
        this.userId = user.getId();
        this.name = user.getUsername();
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }
}
