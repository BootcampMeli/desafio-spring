package com.melidh.desafiospring.domain;

import com.melidh.desafiospring.domain.dto.PostDTO;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String type;
    private String brand;
    private String color;
    private Double price;
    private String notes;
    private Integer category;

    @OneToMany(mappedBy = "product")
    private List<Post> posts = new ArrayList<>();

    public Product(){}

    public Product(Integer id, String name, String type, String brand, String color, Double price, String notes, Integer category) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.brand = brand;
        this.color = color;
        this.price = price;
        this.notes = notes;
        this.category = category;
    }

    public Product(PostDTO post){
        this.name = post.getDetail().getProductName();
        this.type = post.getDetail().getType();
        this.brand = post.getDetail().getBrand();
        this.color = post.getDetail().getColor();
        this.price = post.getPrice();
        this.notes = post.getDetail().getNotes();
        this.category = post.getCategory();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(getId(), product.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
