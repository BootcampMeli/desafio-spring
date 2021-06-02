package br.com.socialmeli.service.impl;

import br.com.socialmeli.api.dto.PostDTO;
import br.com.socialmeli.api.dto.utils.DTOUtils;
import br.com.socialmeli.api.dto.UserPostDTO;
import br.com.socialmeli.model.entity.Post;
import br.com.socialmeli.model.entity.User;
import br.com.socialmeli.model.repository.PostRepository;
import br.com.socialmeli.service.PostService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    //TODO melhorar, acrescentar ordenação e rever o passo
    @Override
    public UserPostDTO getAllPost(long id) {
        User user = userService.getUserById(id);

        return DTOUtils.map(user, UserPostDTO.class);
    }

//    private void orderPosts(User user){
//        List<Post> posts = new ArrayList<>();
//        LocalDate today = LocalDate.now();
//        for (User u:user.getFollowed()
//             ) {
//            pos
//
//        }
//    }
}
