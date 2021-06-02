package br.com.socialmeli.api.dto;

import br.com.socialmeli.model.entity.Post;
import br.com.socialmeli.model.entity.Product;
import br.com.socialmeli.model.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class PostDTO {
    private Long userId;
    @JsonIgnore
    private Date date = new Date();

    private Product detail;

    @NotNull
    private int category;

    @NotNull
    private BigDecimal price;
}
