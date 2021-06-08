package br.com.socialmeli.api.dto;

import br.com.socialmeli.model.entity.Post;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserPostDTO {

    @JsonProperty(value = "userId")
    private long id;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String userName;
    private List<Post> posts;
}
