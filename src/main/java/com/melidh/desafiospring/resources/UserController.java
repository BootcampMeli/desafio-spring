package com.melidh.desafiospring.resources;

import com.melidh.desafiospring.domain.User;
import com.melidh.desafiospring.domain.dto.BaseUserDTO;
import com.melidh.desafiospring.domain.dto.UserFollowCountDTO;
import com.melidh.desafiospring.domain.dto.UserFollowedDTO;
import com.melidh.desafiospring.domain.dto.UserFollowersDTO;
import com.melidh.desafiospring.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<BaseUserDTO>> findAll(){
        List<BaseUserDTO> baseUserDTOList = userService.findAll();

        return ResponseEntity.ok().body(baseUserDTOList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseUserDTO> findCustomer(@PathVariable Integer id){
        User user = userService.findById(id);
        BaseUserDTO baseUserDTO = new BaseUserDTO(user);

        return ResponseEntity.ok().body(baseUserDTO);
    }

    @PostMapping("/{userId}/follow/{userIdToFollow}")
    public ResponseEntity<Void> addFollower(@PathVariable Integer userId, @PathVariable Integer userIdToFollow){
        userService.followSeller(userId,userIdToFollow);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/followers/count")
    public ResponseEntity<UserFollowCountDTO> getFollowersCount(@PathVariable Integer id){
        UserFollowCountDTO userFollowCountDTO = userService.getFollowersCount(id);

        return ResponseEntity.ok().body(userFollowCountDTO);
    }

    @GetMapping("/{id}/followers/list")
    public ResponseEntity<UserFollowersDTO> getFollowersList(
            @PathVariable Integer id,
            @RequestParam(name="order", defaultValue = "name_asc") String orderBy
    ){
        UserFollowersDTO userFollowersDTO = userService.getUserAndFollowers(id,orderBy);

        return ResponseEntity.ok().body(userFollowersDTO);
    }

    @GetMapping("/{id}/followed/list")
    public ResponseEntity<UserFollowedDTO> getFollowedList(
            @PathVariable Integer id,
            @RequestParam(name="order", defaultValue = "name_asc") String orderBy
    ){
        UserFollowedDTO userFollowedDTO = userService.getUserAndFollowed(id,orderBy);

        return ResponseEntity.ok().body(userFollowedDTO);
    }

    @PostMapping("/{userId}/unfollow/{userIdToUnfollow}")
    public ResponseEntity<Void> removeFollowed(@PathVariable Integer userId, @PathVariable Integer userIdToUnfollow){
        userService.unfollow(userId,userIdToUnfollow);

        return ResponseEntity.ok().build();
    }

}
