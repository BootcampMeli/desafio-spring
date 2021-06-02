package br.com.socialmeli.service;

import br.com.socialmeli.api.dto.UserFollowedDto;
import br.com.socialmeli.api.dto.UserFollowersCountDTO;
import br.com.socialmeli.api.dto.UserFollowersDto;
import br.com.socialmeli.model.entity.User;

import java.util.Optional;

public interface UserService {

    Iterable<User> getAllUsers();
    User getUserById(Long userId);
    User saveUser(User user);
    void follow(Long userId, Long userIdToFollow);
    UserFollowersCountDTO getCounterFollowers(Long userId);
    UserFollowersDto getFollowersList(Long userId);
    UserFollowedDto getFollowedList(Long userId);
    void unfollow(Long userId, Long userIdToFollow);

}
