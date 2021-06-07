package com.mercadolivre.socialmeli.dto;

import com.mercadolivre.socialmeli.entities.User;

public class UserFollowersCountDTO extends UserDTO{
    private Integer followersCount;

    public UserFollowersCountDTO(User user) {
        super(user);
    }

    public Integer getFollowersCount() {
        return followersCount;
    }

    public void setFollowersCount(Integer followersCount) {
        this.followersCount = followersCount;
    }

}
