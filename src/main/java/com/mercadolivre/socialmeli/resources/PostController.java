package com.mercadolivre.socialmeli.resources;

import com.mercadolivre.socialmeli.dto.PostDTO;
import com.mercadolivre.socialmeli.dto.PromoPostDTO;
import com.mercadolivre.socialmeli.dto.UserPostsDTO;
import com.mercadolivre.socialmeli.dto.UserPromoPostsCountDTO;
import com.mercadolivre.socialmeli.services.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class PostController {

    private PostService service;

    public PostController(PostService service) {
        this.service = service;
    }

    @RequestMapping(value = "/newpost", method = RequestMethod.POST)
    public ResponseEntity<Void> newPost(@RequestBody PostDTO postDTO){
        service.createPost(postDTO);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/newpromopost", method = RequestMethod.POST)
    public ResponseEntity<Void> newPromoPost(@RequestBody PromoPostDTO promoPostDTO){
        service.createPromoPost(promoPostDTO);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/followed/{userId}/list", method = RequestMethod.GET)
        public ResponseEntity<UserPostsDTO> getUserFollowedPosts(
            @PathVariable Integer userId,
            @RequestParam(value = "order", defaultValue = "date_asc") String orderBy
    ){
        UserPostsDTO userPostsDTO = service.getUserFollowedPosts(userId, orderBy);
        return ResponseEntity.ok(userPostsDTO);
    }

    @RequestMapping(value = "/{userId}/countPromo", method = RequestMethod.GET)
    public ResponseEntity<UserPromoPostsCountDTO> getUserPromoPostsCount(@PathVariable Integer userId){
        UserPromoPostsCountDTO userPromoPostsCountDTO = service.getUserPromoPostsCount(userId);
        return ResponseEntity.ok(userPromoPostsCountDTO);
    }

    @RequestMapping(value = "/{userId}/list", method = RequestMethod.GET)
    public ResponseEntity<UserPostsDTO> getUserPromoPostsListById(@PathVariable Integer userId){
        UserPostsDTO userPostsDTO = service.getUserPromoPostsList(userId);
        return ResponseEntity.ok(userPostsDTO);
    }
}
