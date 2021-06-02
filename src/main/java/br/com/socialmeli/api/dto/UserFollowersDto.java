package br.com.socialmeli.api.dto;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class UserFollowersDto {
    private long id;
    private String userName;
    private Set<UserDTO> followers = new HashSet<>();
}
