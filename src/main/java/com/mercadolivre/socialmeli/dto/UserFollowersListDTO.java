package com.mercadolivre.socialmeli.dto;

import com.mercadolivre.socialmeli.entities.User;

import java.util.ArrayList;
import java.util.List;

public class UserFollowersListDTO extends UserDTO{

    private List<UserDTO> followers = new ArrayList<>();

    public UserFollowersListDTO(User user) {
        super(user);
    }

    public List<UserDTO> getFollowers() {
        return followers;
    }

    public void setFollowers(List<UserDTO> followers) {
        this.followers = followers;
    }
}
