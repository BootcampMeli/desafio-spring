package br.com.socialmeli.api.dto;

import lombok.Data;

@Data
public class UserFollowersCountDTO {
    private long id;
    private String userName;
    private int followerCount;
}
