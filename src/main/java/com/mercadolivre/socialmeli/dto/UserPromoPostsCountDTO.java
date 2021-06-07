package com.mercadolivre.socialmeli.dto;

import com.mercadolivre.socialmeli.entities.User;

public class UserPromoPostsCountDTO extends UserDTO{

    private Integer promoproducts_count;

    public UserPromoPostsCountDTO(User user, Integer promoproducts_count) {
        super(user);
        this.promoproducts_count = promoproducts_count;
    }

    public Integer getPromoproducts_count() {
        return promoproducts_count;
    }

    public void setPromoproducts_count(Integer promoproducts_count) {
        this.promoproducts_count = promoproducts_count;
    }
}
