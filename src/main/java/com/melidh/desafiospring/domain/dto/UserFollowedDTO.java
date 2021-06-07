package com.melidh.desafiospring.domain.dto;

import com.melidh.desafiospring.domain.User;

import java.util.ArrayList;
import java.util.List;

public class UserFollowedDTO extends BaseUserDTO{

    private List<BaseUserDTO> followed = new ArrayList<>();

    public UserFollowedDTO(){}

    public UserFollowedDTO(User user){
        super(user);
    }

    public List<BaseUserDTO> getFollowed() {
        return followed;
    }

    public void setFollowed(List<BaseUserDTO> followed) {
        this.followed = followed;
    }
}
