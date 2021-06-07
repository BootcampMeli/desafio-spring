package com.melidh.desafiospring.domain.dto;

import com.melidh.desafiospring.domain.User;

public class UserPromoCountDTO extends BaseUserDTO{

    private long promoproducts_count;

    public UserPromoCountDTO(){}

    public UserPromoCountDTO(User user){
        super(user);
        promoproducts_count = user.getPosts().stream().filter(u -> u.isPromo()).count();
    }

    public long getPromoproducts_count() {
        return promoproducts_count;
    }

    public void setPromoproducts_count(long promoproducts_count) {
        this.promoproducts_count = promoproducts_count;
    }
}
