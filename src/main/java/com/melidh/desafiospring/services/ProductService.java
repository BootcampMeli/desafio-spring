package com.melidh.desafiospring.services;

import com.melidh.desafiospring.domain.Post;
import com.melidh.desafiospring.domain.Product;
import com.melidh.desafiospring.domain.User;
import com.melidh.desafiospring.domain.dto.PostDTO;
import com.melidh.desafiospring.domain.dto.UserPostsDTO;
import com.melidh.desafiospring.domain.dto.UserPromoCountDTO;
import com.melidh.desafiospring.repositories.ProductRepository;
import com.melidh.desafiospring.services.exceptions.DateParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    public void addPost(PostDTO postDTO) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

        Product product = new Product(postDTO);
        Post post = new Post();

        try {
            User user = userService.findById(postDTO.getUserId());

            post.setDate(formatter.parse(postDTO.getDate()));
            post.setProduct(product);
            post.setUser(user);
            post.setPromo(postDTO.isHasPromo());
            post.setDiscount(postDTO.getDiscount());

            productRepository.save(product);
            postService.save(post);

        } catch (ParseException e) {
            e.printStackTrace();
            throw new DateParseException("Invalid date input. Check if date format is correct.");
        }

    }

    public UserPostsDTO findAllRecentPosts(Integer userId, String orderBy) {
        User user = userService.findById(userId);
        List<Integer> following_ids = user.getFollowing().stream().map(u -> u.getId()).collect(Collectors.toList());
        List<Post> posts = postService.findRecentPosts(following_ids);
        sortByDate(posts, orderBy);

        UserPostsDTO userPostsDTO = new UserPostsDTO();
        userPostsDTO.setUserId(userId);
        userPostsDTO.setUserName(user.getUsername());
        userPostsDTO.getPosts().addAll(posts.stream().map(p -> new PostDTO(p)).collect(Collectors.toList()));

        return userPostsDTO;
    }

    private void sortByDate(List<Post> posts, String orderBy){
        String[] order = orderBy.split("_");

        if (order[1].equals("asc")) {
            Collections.sort(posts, Comparator.comparing(Post::getDate));
        } else {
            Collections.sort(posts, Comparator.comparing(Post::getDate).reversed());
        }
    }

    public UserPromoCountDTO getUserPromoCount(Integer userId) {
        UserPromoCountDTO userPromoCount = new UserPromoCountDTO(userService.findById(userId));

        return userPromoCount;
    }

    public UserPostsDTO findAllUserPromoPosts(Integer userId) {
        User user = userService.findById(userId);
        List<Post> posts = user.getPosts().stream().filter(post -> post.isPromo()).collect(Collectors.toList());

        UserPostsDTO userPostsDTO = new UserPostsDTO();
        userPostsDTO.setUserId(userId);
        userPostsDTO.setUserName(user.getUsername());
        userPostsDTO.getPosts().addAll(posts.stream().map(p -> new PostDTO(p)).collect(Collectors.toList()));

        return userPostsDTO;
    }
}
