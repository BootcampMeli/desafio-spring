package br.com.mercadolivre.desafiospring.resources;

import br.com.mercadolivre.desafiospring.dto.FeedPostsDTO;
import br.com.mercadolivre.desafiospring.dto.PostDTO;
import br.com.mercadolivre.desafiospring.services.PostService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/products")
public class ProductController {

    final PostService postService;

    public ProductController(PostService postService) {
        this.postService = postService;
    }

    @ApiOperation(value = "Create new Post")
    @RequestMapping(value = "/newpost", method = RequestMethod.POST, consumes="application/json")
    public ResponseEntity<Void> newpost(@RequestBody PostDTO postDTO) {
        postService.insert(postDTO);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "Followed Salesman Post")
    @RequestMapping(value = "/followed/{userID}/list", method = RequestMethod.GET)
    public ResponseEntity<FeedPostsDTO> followedUsersPost(
            @PathVariable Integer userID,
            @RequestParam(value = "order", defaultValue = "date_asc") String orderBy) {
        FeedPostsDTO followedUsersPost = postService.getFollowedUsersPost(userID, orderBy);
        return ResponseEntity.ok().body(followedUsersPost);
    }
}
