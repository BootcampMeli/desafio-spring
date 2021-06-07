package com.melidh.desafiospring.services;

import com.melidh.desafiospring.domain.Post;
import com.melidh.desafiospring.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    private final Integer POST_AGE = -14;

    public void save(Post post){
        postRepository.save(post);
    }

    public List<Post> findRecentPosts(List<Integer> following_ids) {

        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_WEEK, POST_AGE);

        List<Post> posts = postRepository.findAllRecentPostOfFollowed(following_ids, c.getTime());

        return posts;
    }
}
