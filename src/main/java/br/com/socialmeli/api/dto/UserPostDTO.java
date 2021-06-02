package br.com.socialmeli.api.dto;

import br.com.socialmeli.model.entity.Post;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Set;

@Data
public class UserPostDTO {

    @JsonProperty(value = "userId")
    private long id;
    private Set<Post> posts;
}
