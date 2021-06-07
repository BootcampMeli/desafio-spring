package com.mercadolivre.socialmeli.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "tb_post")
public class Post {

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private User user;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id_post;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate date;

    @OneToOne
    private Product detail;

    private Integer category;
    private Double price;
    private Boolean hasPromo;
    private Double discount;

    public Post(){}

    public Post(User user, LocalDate date, Product detail, Integer category, Double price) {
        this.user = user;
        this.date = date;
        this.detail = detail;
        this.category = category;
        this.price = price;
    }

    public Post(User user, LocalDate date, Product detail, Integer category, Double price, Boolean hasPromo, Double discount) {
        this.user = user;
        this.date = date;
        this.detail = detail;
        this.category = category;
        this.price = price;
        this.hasPromo = hasPromo;
        this.discount = discount;
    }

    public User getUser() {
        return user;
    }

    public Integer getId_post() {
        return id_post;
    }

    public LocalDate getDate() {
        return date;
    }

    public Product getDetail() {
        return detail;
    }

    public Integer getCategory() {
        return category;
    }

    public Double getPrice() {
        return price;
    }

    public Boolean getHasPromo() {
        return hasPromo;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setHasPromo(Boolean hasPromo) {
        this.hasPromo = hasPromo;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Post)) return false;
        Post post = (Post) o;
        return id_post.equals(post.id_post);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_post);
    }
}
