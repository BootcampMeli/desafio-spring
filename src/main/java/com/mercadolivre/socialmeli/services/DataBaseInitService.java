package com.mercadolivre.socialmeli.services;

import com.mercadolivre.socialmeli.entities.Post;
import com.mercadolivre.socialmeli.entities.Product;
import com.mercadolivre.socialmeli.entities.User;
import com.mercadolivre.socialmeli.repositories.PostRepository;
import com.mercadolivre.socialmeli.repositories.ProductRepository;
import com.mercadolivre.socialmeli.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;

@Service
public class DataBaseInitService {
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final ProductRepository productRepository;

    public DataBaseInitService(UserRepository userRepository, PostRepository postRepository, ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.productRepository = productRepository;
    }

    public void initTestDataBase(){
        User user1 = new User("gio", false);
        User user2 = new User("felu", false);
        User seller1 = new User("phill", true);
        User seller2 = new User("jean", true);
        User seller3 = new User("gus", true);

        seller1.setFollowed(new HashSet<>(Arrays.asList(user1, seller2)));
        seller2.setFollowed(new HashSet<>(Arrays.asList(user2, user1)));
        seller3.setFollowed(new HashSet<>(Arrays.asList(user1, user2, seller1, seller2)));

        userRepository.saveAll(Arrays.asList(user1, user2, seller1, seller2, seller3));

        Product p1 = new Product("K380", "keyboard", "Logitech", "black", "US format");
        Product p2 = new Product("Marker kit", "pens", "Faber-Castell", "multi", "Top kit");
        Product p3 = new Product("Cotton blanket", "single dorm", "Santista", "blue", "200 strings");

        Post post1 = new Post(seller1,LocalDate.now(),p1,1,236.0,false, 0.0);
        Post post2 = new Post(seller2,LocalDate.now().minusDays(2),p2,1,18.0, false, 0.0);
        Post post3 = new Post(seller3,LocalDate.now().minusDays(5),p3,3,89.0,true,0.2);

        productRepository.saveAll(Arrays.asList(p1,p2,p3));
        postRepository.saveAll(Arrays.asList(post1,post2,post3));
    }

}
