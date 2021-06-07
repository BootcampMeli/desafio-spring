package com.mercadolivre.socialmeli.resources;

import com.mercadolivre.socialmeli.dto.UserFollowedListDTO;
import com.mercadolivre.socialmeli.dto.UserFollowersCountDTO;
import com.mercadolivre.socialmeli.dto.UserFollowersListDTO;
import com.mercadolivre.socialmeli.entities.User;
import com.mercadolivre.socialmeli.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/users")
public class UserController {

    private UserService service;

    public UserController(UserService service){
        this.service = service;
    }

    @RequestMapping(value = "/{userId}/follow/{userIdToFollow}", method = RequestMethod.POST)
    public ResponseEntity<Void> follow(@PathVariable Integer userId, @PathVariable Integer userIdToFollow){
        User user = service.follow(userId, userIdToFollow);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/{userId}/followers/count", method = RequestMethod.GET)
    public ResponseEntity<UserFollowersCountDTO> getUserFollowersCount(@PathVariable Integer userId){
        UserFollowersCountDTO userFollowersCount = service.getUserFollowersCount(userId);
        return ResponseEntity.ok(userFollowersCount);
    }

    @RequestMapping(value = "/{userId}/followers/list", method = RequestMethod.GET)
    public ResponseEntity<UserFollowersListDTO> getUserFollowersList(
            @PathVariable Integer userId,
            @RequestParam(value = "order", defaultValue = "name_asc") String orderBy){
        UserFollowersListDTO userFollowersListDTO = service.getUserFollowersList(userId, orderBy);
        return ResponseEntity.ok(userFollowersListDTO);
    }

    @RequestMapping(value = "/{userId}/followed/list", method = RequestMethod.GET)
    public ResponseEntity<UserFollowedListDTO> getUserFollowedList(
            @PathVariable Integer userId,
            @RequestParam(value = "order", defaultValue = "name_asc") String orderBy){
        UserFollowedListDTO userFollowedListDTO = service.getUserFollowedList(userId, orderBy);
        return ResponseEntity.ok(userFollowedListDTO);
    }

    @RequestMapping(value="/{userId}/unfollow/{userIdToUnfollow}", method = RequestMethod.POST)
    public ResponseEntity<Void> unfollow(@PathVariable Integer userId, @PathVariable Integer userIdToUnfollow){
        User user = service.unfollow(userId, userIdToUnfollow);
        return ResponseEntity.ok().build();
    }
}
