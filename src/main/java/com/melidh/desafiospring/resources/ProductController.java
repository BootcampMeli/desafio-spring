package com.melidh.desafiospring.resources;

import com.melidh.desafiospring.domain.dto.PostDTO;
import com.melidh.desafiospring.domain.dto.UserPostsDTO;
import com.melidh.desafiospring.domain.dto.UserPromoCountDTO;
import com.melidh.desafiospring.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/newpost")
    public ResponseEntity<Void> postNewProduct(@Valid @RequestBody PostDTO postDTO) {
        System.out.println(postDTO);
        productService.addPost(postDTO);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/followed/{userId}/list")
    public ResponseEntity<UserPostsDTO> getRecentPosts(
            @PathVariable Integer userId,
            @RequestParam(name = "order", defaultValue = "date_asc") String orderBy) {
        UserPostsDTO userPostsDTO = productService.findAllRecentPosts(userId, orderBy);

        return ResponseEntity.ok().body(userPostsDTO);
    }

    @GetMapping("/{userId}/countPromo")
    public ResponseEntity<UserPromoCountDTO> getUserPromoCount(@PathVariable Integer userId){
        UserPromoCountDTO userPromoCountDTO = productService.getUserPromoCount(userId);

        return ResponseEntity.ok().body(userPromoCountDTO);
    }

    @GetMapping("/{userId}/list")
    public ResponseEntity<UserPostsDTO> getAllUserPromoPosts(@PathVariable Integer userId){
        UserPostsDTO userPostsDTO = productService.findAllUserPromoPosts(userId);

        return ResponseEntity.ok().body(userPostsDTO);
    }
}
