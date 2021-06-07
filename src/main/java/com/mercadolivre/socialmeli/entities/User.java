package com.mercadolivre.socialmeli.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "tb_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String username;

    @ManyToMany
    @JoinTable(name="user_follows",
            joinColumns={@JoinColumn(name="user_id")},
            inverseJoinColumns={@JoinColumn(name="followed_id")})
    private Set<User> followed = new HashSet<>();

    @ManyToMany(mappedBy = "followed")
    private Set<User> followers = new HashSet<>();

    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Post> posts = new ArrayList<>();

    private Boolean isSeller;

    public User(){}

    public User(String username, Boolean isSeller) {
        this.username = username;
        this.isSeller = isSeller;
    }

    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public Set<User> getFollowed() {
        return followed;
    }

    public Set<User> getFollowers() {
        return followers;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public Boolean getIsSeller(){return isSeller;}

    public void setFollowed(Set<User> followed) {
        this.followed = followed;
    }

    public void setFollowers(Set<User> followers) {
        this.followers = followers;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setSeller(Boolean seller) {
        isSeller = seller;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return id.equals(user.id) && username.equals(user.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username);
    }
}
