package br.com.socialmeli.service;

import br.com.socialmeli.api.dto.PostDTO;
import br.com.socialmeli.api.dto.PostPromoDTO;
import br.com.socialmeli.api.dto.UserPostDTO;
import br.com.socialmeli.api.dto.UserSellerPromoProductsDTO;
import br.com.socialmeli.model.entity.Post;

import java.util.List;

public interface PostService {
    void newpost(PostDTO postDTO);
    List<Post> getAllPostByUserId(long id);
    List<Post> getSellerPostFollwedByUser(long id);
    UserPostDTO getUserSellerPostList(long userId);
    UserPostDTO getPromoProductsSeller(Long userId);
    void newpostPromo(PostPromoDTO postPromoDTO);
    UserSellerPromoProductsDTO getPromoProductsSellerCounter(Long userId);
}
