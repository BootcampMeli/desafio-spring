package com.mercadolivre.socialmeli.dto;

import com.mercadolivre.socialmeli.entities.Post;

import java.util.ArrayList;
import java.util.List;

public class UserPostsDTO {
    private Integer userId;
    private List<Post> posts = new ArrayList<>();

    public Integer getUserId() {
        return userId;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setUserPosts(List<Post> userPosts) {
        this.posts = userPosts;
    }
}
