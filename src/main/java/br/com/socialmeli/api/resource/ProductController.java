package br.com.socialmeli.api.resource;

import br.com.socialmeli.api.dto.PostDTO;
import br.com.socialmeli.api.dto.UserPostDTO;
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
    public void addNewPost(@RequestBody PostDTO postDTO) {
        postService.newpost(postDTO);
    }

    @ApiOperation("Get list of posts from a user")
    @GetMapping(value = "/followed/{userId}/list")
    @ResponseStatus(HttpStatus.CREATED)
    public UserPostDTO getUserPosts(@PathVariable Long userId) {
        return postService.getAllPost(userId);
    }


}
