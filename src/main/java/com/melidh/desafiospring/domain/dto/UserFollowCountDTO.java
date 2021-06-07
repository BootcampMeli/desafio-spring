package com.melidh.desafiospring.domain.dto;

import com.melidh.desafiospring.domain.User;

public class UserFollowCountDTO extends BaseUserDTO{

    private long followers_count;

    public UserFollowCountDTO(){}

    public UserFollowCountDTO(User user){
        super(user);
        followers_count = user.getFollowers().stream().count();
    }

    public long getFollowers_count() {
        return followers_count;
    }

    public void setFollowers_count(long followers_count) {
        this.followers_count = followers_count;
    }
}
