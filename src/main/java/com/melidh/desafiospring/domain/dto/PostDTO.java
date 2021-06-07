package com.melidh.desafiospring.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.melidh.desafiospring.domain.Post;
import com.melidh.desafiospring.resources.filters.DiscountFilter;
import com.melidh.desafiospring.resources.filters.PromoFilter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.text.SimpleDateFormat;

public class PostDTO {

    @NotNull
    private Integer userId;
    private Integer id_post;

    @NotNull
    private String date;
    private Detail detail;

    @NotNull
    private Integer category;

    @NotNull
    @Positive
    private Double price;

    @JsonInclude(value = JsonInclude.Include.CUSTOM, valueFilter = PromoFilter.class)
    private boolean hasPromo = false;

    @JsonInclude(value = JsonInclude.Include.CUSTOM, valueFilter = DiscountFilter.class)
    private Double discount = 0.0;

    public PostDTO(){}

    public PostDTO(Post post){
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        this.userId = post.getUser().getId();
        this.id_post = post.getId();
        this.date = sdf.format(post.getDate());
        this.detail = new Detail(post.getProduct());
        this.category = post.getProduct().getCategory();
        this.price = post.getProduct().getPrice();
        this.hasPromo = post.isPromo();
        this.discount = post.getDiscount();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getId_post() {
        return id_post;
    }

    public void setId_post(Integer id_post) {
        this.id_post = id_post;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Detail getDetail() {
        return detail;
    }

    public void setDetail(Detail detail) {
        this.detail = detail;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public boolean isHasPromo() {
        return hasPromo;
    }

    public void setHasPromo(boolean hasPromo) {
        this.hasPromo = hasPromo;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }
}
