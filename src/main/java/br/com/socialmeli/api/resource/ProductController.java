package br.com.socialmeli.api.resource;

import br.com.socialmeli.api.dto.PostDTO;
import br.com.socialmeli.api.dto.PostPromoDTO;
import br.com.socialmeli.api.dto.UserPostDTO;
import br.com.socialmeli.api.dto.UserSellerPromoProductsDTO;
import br.com.socialmeli.service.impl.PostServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Api(tags = "Products", value = "/products")
@RestController
@RequestMapping(value = "/products")
public class ProductController {

    private PostServiceImpl postService;

    public ProductController(PostServiceImpl postService) {
        this.postService = postService;
    }

    @ApiOperation("Add new post")
    @PostMapping(value = "/newpost")
    @ResponseStatus(HttpStatus.CREATED)
    public void addNewPost(@RequestBody PostDTO postDTO) {
        postService.newpost(postDTO);
    }

    @ApiOperation("Add new post")
    @PostMapping(value = "/newpromopost")
    @ResponseStatus(HttpStatus.CREATED)
    public void addNewPostPromo(@RequestBody PostPromoDTO postPromoDTO) {
        postService.newpostPromo(postPromoDTO);
    }

    @ApiOperation("Get list of posts from a user")
    @GetMapping(value = "/followed/{userId}/list")
    public UserPostDTO getUserPosts(@PathVariable Long userId) {
        return postService.getUserSellerPostList(userId);
    }

    @ApiOperation("Get list of posts with promo")
    @GetMapping(value = "/{userId}/list/promoproducts")
    public UserPostDTO getPromoProductsSeller(@PathVariable Long userId) {
        return postService.getPromoProductsSeller(userId);
    }

    @ApiOperation("Get list of all promo products of a seller")
    @GetMapping(value = "/{userId}/countPromo/")
    public UserSellerPromoProductsDTO getPromoProductsSellerCounter(@PathVariable Long userId) {
        return postService.getPromoProductsSellerCounter(userId);
    }


}
