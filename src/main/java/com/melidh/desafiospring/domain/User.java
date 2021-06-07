package com.melidh.desafiospring.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;

    @ManyToMany
    @JoinTable(name="FOLLOWERS_FOLLOWING",
            joinColumns= @JoinColumn(name="seller_id"),
            inverseJoinColumns=@JoinColumn(name="user_id"))
    private Set<User> followers = new HashSet<User>();

    @JsonIgnore
    @ManyToMany(mappedBy = "followers")
    private Set<User> following = new HashSet<User>();

    @OneToMany(mappedBy = "user")
    private List<Post> posts = new ArrayList<>();

    public User(){}

    public User(Integer id, String username) {
        this.id = id;
        this.username = username;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String name) {
        this.username = name;
    }

    public Set<User> getFollowers() {
        return followers;
    }

    public void setFollowers(Set<User> followers) {
        this.followers = followers;
    }

    public Set<User> getFollowing() {
        return following;
    }

    public void setFollowing(Set<User> following) {
        this.following = following;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
}
