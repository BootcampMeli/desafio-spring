package br.com.socialmeli.api.resource;

import br.com.socialmeli.api.dto.UserFollowedDto;
import br.com.socialmeli.api.dto.UserFollowersCountDTO;
import br.com.socialmeli.api.dto.UserFollowersDto;
import br.com.socialmeli.model.entity.User;
import br.com.socialmeli.service.impl.UserServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@Api(tags = "Users", value = "/users")
@RestController
@RequestMapping(value = "/users")
public class UserController {

	private UserServiceImpl userService;

	public UserController(UserServiceImpl userService) {
		this.userService = userService;
	}

	@ApiOperation("Get all users")
	@GetMapping
	public Iterable<User> listUsers() {
		return userService.getAllUsers();
	}

	@ApiOperation("Follow Action")
	@PostMapping(value = "/{userId}/follow/{userIdToFollow}")
	public void follow(@PathVariable Long userId, @PathVariable Long userIdToFollow) {
		userService.follow(userId, userIdToFollow);
	}

	@ApiOperation("Get amount of followers from a user")
	@GetMapping(value = "/{userId}/followers/count/")
	public UserFollowersCountDTO listUsers(@PathVariable Long userId) {
		return userService.getCounterFollowers(userId);
	}

	@ApiOperation("Get list of followers from a user")
	@GetMapping(value = "/{userId}/followers/list/")
	public UserFollowersDto listFollowersUsers(@PathVariable Long userId) {
		return userService.getFollowersList(userId);
	}

	@ApiOperation("Get list of followed from a user")
	@GetMapping(value = "/{userId}/followed/list/")
	public UserFollowedDto listFollowedUsers(@PathVariable Long userId) {
		return userService.getFollowedList(userId);
	}

	@ApiOperation("Unfollow Action")
	@PostMapping(value = "/{userId}/unfollow/{userIdToUnfollow}")
	public void unfollow(@PathVariable Long userId, @PathVariable Long userIdToUnfollow) {
		userService.unfollow(userId, userIdToUnfollow);
	}
}
