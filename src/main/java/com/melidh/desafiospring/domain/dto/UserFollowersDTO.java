package com.melidh.desafiospring.domain.dto;

import com.melidh.desafiospring.domain.User;

import java.util.ArrayList;
import java.util.List;

public class UserFollowersDTO extends BaseUserDTO{

    private List<BaseUserDTO> followers = new ArrayList<>();

    public UserFollowersDTO(){}

    public UserFollowersDTO(User user){
        super(user);
    }

    public List<BaseUserDTO> getFollowers() {
        return followers;
    }

    public void setFollowers(List<BaseUserDTO> followers) {
        this.followers = followers;
    }
}
