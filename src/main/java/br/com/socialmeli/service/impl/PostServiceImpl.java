package br.com.socialmeli.service.impl;

import br.com.socialmeli.api.dto.PostDTO;
import br.com.socialmeli.api.dto.PostPromoDTO;
import br.com.socialmeli.api.dto.UserPostDTO;
import br.com.socialmeli.api.dto.UserSellerPromoProductsDTO;
import br.com.socialmeli.api.dto.utils.DTOUtils;
import br.com.socialmeli.api.exception.NotPermittedActionException;
import br.com.socialmeli.model.entity.Post;
import br.com.socialmeli.model.entity.User;
import br.com.socialmeli.model.repository.PostRepository;
import br.com.socialmeli.service.PostService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Log4j2
@Service
public class PostServiceImpl implements PostService {

    private UserServiceImpl userService;
    private PostRepository postRepository;

    public PostServiceImpl(UserServiceImpl userService, PostRepository postRepository) {
        this.userService = userService;
        this.postRepository = postRepository;
    }

    @Override
    public void newpost(PostDTO postDTO) {
        User user = userService.getUserById(postDTO.getUserId());

        if(!user.isSeller()){
            throw new NotPermittedActionException("A regular user cannot create post");
        }

        Post post = new Post().builder()
                .category(postDTO.getCategory())
                .date(postDTO.getDate())
                .detail(postDTO.getDetail())
                .price(postDTO.getPrice())
                .user(user)
                .build();

        user.addPost(post);
        userService.saveUser(user);
    }

    @Override
    public List<Post> getAllPostByUserId(long id) {
        User user = userService.getUserById(id);
        return DTOUtils.mapAll(user.getPosts(), Post.class);
    }

    @Override
    public List<Post> getSellerPostFollwedByUser(long userId) {
        List<Post> listPost = new ArrayList<>();
        Date twoWeeks = Date.valueOf(LocalDate.now().minusWeeks(2));

        User user = userService.getUserById(userId);

        for (User u : user.getFollowed()) {
            List<Post> posts = postRepository.findByUserIdAndDateAfter(u.getId(), twoWeeks);
            listPost.addAll(posts);
        }

        return listPost;
    }

    @Override
    public UserPostDTO getUserSellerPostList(long userId) {
        UserPostDTO userPostDTO = new UserPostDTO();
        userPostDTO.setId(userId);
        userPostDTO.setPosts(getSellerPostFollwedByUser(userId));
        return userPostDTO;
    }

    @Override
    public UserPostDTO getPromoProductsSeller(Long userId) {
        User user = userService.getUserById(userId);

        if(!user.isSeller()){
            throw new NotPermittedActionException("The user must be a seller.");
        }

        List<Post> promoProducts = postRepository.findByUserIdAndHasPromo(userId, true);

        return new UserPostDTO().builder()
                .id(user.getId())
                .userName(user.getUserName())
                .posts(promoProducts)
                .build();
    }

    @Override
    public void newpostPromo(PostPromoDTO postPromoDTO) {
        User user = userService.getUserById(postPromoDTO.getUserId());

        if(!user.isSeller()){
            throw new NotPermittedActionException("A regular user cannot create post");
        }

        Post post = new Post().builder()
                .category(postPromoDTO.getCategory())
                .date(postPromoDTO.getDate())
                .detail(postPromoDTO.getDetail())
                .price(postPromoDTO.getPrice())
                .user(user)
                .discount(postPromoDTO.getDiscount())
                .hasPromo(postPromoDTO.isHasPromo())
                .build();

        user.addPost(post);
        userService.saveUser(user);
    }

    @Override
    public UserSellerPromoProductsDTO getPromoProductsSellerCounter(Long userId) {
        User user = userService.getUserById(userId);

        if(!user.isSeller()){
            throw new NotPermittedActionException("The user must be a seller.");
        }

        List<Post> promoProducts = postRepository.findByUserIdAndHasPromo(userId, true);

        return new UserSellerPromoProductsDTO().builder()
                .id(user.getId())
                .userName(user.getUserName())
                .promoProductsCount(promoProducts.size())
                .build();
    }

}
