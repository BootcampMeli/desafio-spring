package com.melidh.desafiospring.services;

import com.melidh.desafiospring.domain.User;
import com.melidh.desafiospring.domain.dto.BaseUserDTO;
import com.melidh.desafiospring.domain.dto.UserFollowCountDTO;
import com.melidh.desafiospring.domain.dto.UserFollowedDTO;
import com.melidh.desafiospring.domain.dto.UserFollowersDTO;
import com.melidh.desafiospring.repositories.UserRepository;
import com.melidh.desafiospring.services.exceptions.ActionNotAllowedException;
import com.melidh.desafiospring.services.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User findById(Integer id) {
        Optional<User> userOptional = userRepository.findById(id);

        if (!userOptional.isPresent()) throw new UserNotFoundException("User not found. Id: " + id);

        BaseUserDTO baseUserDTO = new BaseUserDTO(userOptional.get());

        return userOptional.get();
    }

    public void followSeller(Integer userId, Integer userIdToFollow) {

        if (userId == userIdToFollow) throw new ActionNotAllowedException("User cannot follow self");

        User customer = findById(userId);
        User seller = findById(userIdToFollow);

        customer.getFollowing().add(seller);
        seller.getFollowers().add(customer);

        userRepository.save(customer);
    }

    public List<BaseUserDTO> findAll() {
        List<User> customers = userRepository.findAll();
        List<BaseUserDTO> baseUserDTOList = customers.stream().map(user -> new BaseUserDTO(user)).collect(Collectors.toList());
        return baseUserDTOList;
    }

    public UserFollowCountDTO getFollowersCount(Integer id) {
        User user = findById(id);
        UserFollowCountDTO userFollowCountDTO = new UserFollowCountDTO(user);

        return userFollowCountDTO;
    }

    public UserFollowersDTO getUserAndFollowers(Integer id, String orderBy) {
        User user = findById(id);
        UserFollowersDTO userFollowers = new UserFollowersDTO(user);

        List<BaseUserDTO> baseUsers = user.getFollowers().stream().map(u -> new BaseUserDTO(u)).collect(Collectors.toList());
        userFollowers.getFollowers().addAll(baseUsers);

        sortByName(userFollowers.getFollowers(),orderBy);

        return userFollowers;
    }

    public UserFollowedDTO getUserAndFollowed(Integer id, String orderBy) {
        User user = findById(id);
        UserFollowedDTO userFollowed = new UserFollowedDTO(user);

        List<BaseUserDTO> baseUsers = user.getFollowing().stream().map(u -> new BaseUserDTO(u)).collect(Collectors.toList());
        userFollowed.getFollowed().addAll(baseUsers);

        sortByName(userFollowed.getFollowed(),orderBy);

        return userFollowed;
    }

    public void unfollow(Integer userId, Integer userIdToUnfollow) {
        User user = findById(userId);
        User seller = findById(userIdToUnfollow);

        user.getFollowing().remove(seller);
        seller.getFollowers().remove(user);

        userRepository.save(user);
    }

    private void sortByName(List<BaseUserDTO> users, String orderBy) {
        String [] order = orderBy.split("_");

        if (order[1].equals("asc")) {
            Collections.sort(users, Comparator.comparing(BaseUserDTO::getUserName));
        } else {
            Collections.sort(users, Comparator.comparing(BaseUserDTO::getUserName).reversed());
        }
    }

}
