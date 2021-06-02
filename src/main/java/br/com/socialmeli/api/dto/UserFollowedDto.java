package br.com.socialmeli.api.dto;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class UserFollowedDto {
    private long id;
    private String userName;
    private Set<UserDTO> followed = new HashSet<>();
}
