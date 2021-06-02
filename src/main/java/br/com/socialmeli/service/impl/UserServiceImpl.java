package br.com.socialmeli.service.impl;

import br.com.socialmeli.api.dto.UserFollowedDto;
import br.com.socialmeli.api.dto.UserFollowersCountDTO;
import br.com.socialmeli.api.dto.UserFollowersDto;
import br.com.socialmeli.api.dto.utils.DTOUtils;
import br.com.socialmeli.api.exception.NotPermittedActionException;
import br.com.socialmeli.api.exception.ResourceNotFoundException;
import br.com.socialmeli.model.entity.User;
import br.com.socialmeli.model.repository.UserRepository;
import br.com.socialmeli.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;

@Log4j2
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public Iterable<User> getAllUsers() {
		log.info("Find users");
		return userRepository.findAll();
	}

	@Override
	public User getUserById(Long id){
		log.info("Find user by id: " + id);
		User user = userRepository.findById(id).orElseThrow(
				() ->new ResourceNotFoundException("User by id: " + id + " not found"));

		return user;
	}

	@Override
	public User saveUser(User user){
		return userRepository.save(user);
	}

	@Override
	public void follow(Long userId, Long userIdToFollow) {
		User user = getUserById(userId);
		User userToFollow = getUserById(userIdToFollow);

		if(!userToFollow.isSeller()){
			throw new NotPermittedActionException("A regular user cannot be follow");
		}

		user.addFollowed(userToFollow);

		userRepository.saveAll(Arrays.asList(user, userToFollow));
	}

	@Override
	public UserFollowersCountDTO getCounterFollowers(Long userId) {
		User user = getUserById(userId);
		return DTOUtils.map(user, UserFollowersCountDTO.class);
	}

	@Override
	public UserFollowersDto getFollowersList(Long userId) {
		User user = getUserById(userId);
		return DTOUtils.map(user, UserFollowersDto.class);
	}

	@Override
	public UserFollowedDto getFollowedList(Long userId) {
		User user = getUserById(userId);
		return DTOUtils.map(user, UserFollowedDto.class);
	}

	@Override
	public void unfollow(Long userId, Long userIdToFollow) {
		User user = getUserById(userId);
		User userToUnfollow = getUserById(userIdToFollow);

		user.removeFollowed(userToUnfollow);

		userRepository.saveAll(Arrays.asList(user, userToUnfollow));
	}
}
