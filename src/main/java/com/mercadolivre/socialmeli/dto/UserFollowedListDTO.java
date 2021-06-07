package com.mercadolivre.socialmeli.dto;

import com.mercadolivre.socialmeli.entities.User;

import java.util.ArrayList;
import java.util.List;

public class UserFollowedListDTO{
    private final Integer userId;
    private final String username;
    private List<UserDTO> followed = new ArrayList<>();

    public UserFollowedListDTO(User user) {
        this.userId = user.getId();
        this.username = user.getUsername();
    }

    public Integer getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public List<UserDTO> getFollowed() {
        return followed;
    }

    public void setFollowed(List<UserDTO> followed) {
        this.followed = followed;
    }
}
