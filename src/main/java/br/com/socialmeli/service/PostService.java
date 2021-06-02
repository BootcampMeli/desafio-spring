package br.com.socialmeli.service;

import br.com.socialmeli.api.dto.PostDTO;
import br.com.socialmeli.api.dto.UserPostDTO;

public interface PostService {
    void newpost(PostDTO postDTO);
    UserPostDTO getAllPost(long id);
}
