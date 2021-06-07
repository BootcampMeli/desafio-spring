package com.mercadolivre.socialmeli.services;

import com.mercadolivre.socialmeli.dto.*;
import com.mercadolivre.socialmeli.entities.Post;
import com.mercadolivre.socialmeli.entities.Product;
import com.mercadolivre.socialmeli.entities.User;
import com.mercadolivre.socialmeli.repositories.PostRepository;
import com.mercadolivre.socialmeli.repositories.ProductRepository;
import com.mercadolivre.socialmeli.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public PostService(PostRepository postRepository, ProductRepository productRepository, UserRepository userRepository){
        this.postRepository = postRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    public void createPost(PostDTO postDTO){
        Integer userId = postDTO.getUserId();
        User user = userRepository.getById(userId);
        if(!user.getIsSeller()) user.setSeller(true);

        Product product = new Product(postDTO.getDetail());
        Post post = new Post(user, postDTO.getDate(), product, postDTO.getCategory(),
                postDTO.getPrice(), false, 0.0);

        productRepository.save(product);
        postRepository.save(post);
        userRepository.save(user);
    }

    public void createPromoPost(PromoPostDTO promoPostDTO){
        Integer userId = promoPostDTO.getUserId();
        User user = userRepository.getById(userId);
        if(!user.getIsSeller()) user.setSeller(true);

        Product promoProduct = new Product(promoPostDTO.getDetail());
        Post promoPost = new Post(user, promoPostDTO.getDate(), promoProduct, promoPostDTO.getCategory(),
                promoPostDTO.getPrice(), promoPostDTO.getHasPromo(), promoPostDTO.getDiscount());

        productRepository.save(promoProduct);
        postRepository.save(promoPost);
        userRepository.save(user);
    }

    public UserPostsDTO getUserFollowedPosts(Integer userId, String orderBy){
        User user = userRepository.getById(userId);
        UserPostsDTO userPostsDTO = new UserPostsDTO();
        LocalDate twoWeeksAgo = LocalDate.now().minusWeeks(2);

        List<Post> postList = new ArrayList<>();
        for(User followed : user.getFollowers()){
            for(Post post : followed.getPosts()){
                if(!post.getDate().isBefore(twoWeeksAgo))
                postList.add(post);
            }
        }

        List<Post> sortedList = null;
        if(orderBy.equals("date_asc")){
            sortedList = postList.stream().sorted(Comparator.comparing(Post::getDate)).collect(Collectors.toList());
        }
        if(orderBy.equals("date_desc")){
            sortedList = postList.stream().sorted(Comparator.comparing(Post::getDate).reversed()).collect(Collectors.toList());
        }

        userPostsDTO.setUserId(userId);
        userPostsDTO.setUserPosts(sortedList);

        return userPostsDTO;
    }

    public UserPromoPostsCountDTO getUserPromoPostsCount(Integer userId){
        User user = userRepository.getById(userId);
        int count = (int) user.getPosts().stream().filter(Post::getHasPromo).count();
        return new UserPromoPostsCountDTO(user, count);
    }

    public UserPostsDTO getUserPromoPostsList(Integer userId){
        User user = userRepository.getById(userId);
        UserPostsDTO userPostsDTO = new UserPostsDTO();
        List<Post> listOfPromoPosts = new ArrayList<>();

        for(Post p : user.getPosts()){
            if(p.getHasPromo()){
                listOfPromoPosts.add(p);
            }
        }

        userPostsDTO.setUserId(userId);
        userPostsDTO.setUserPosts(listOfPromoPosts);

        return userPostsDTO;
    }
}
