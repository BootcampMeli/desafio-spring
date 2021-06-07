package com.melidh.desafiospring.repositories;

import com.melidh.desafiospring.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {

    @Query("SELECT posts " +
            "FROM Post posts " +
            "WHERE posts.user.id IN :followed_sellers " +
            "AND posts.date BETWEEN :startDate AND current_date " +
            "ORDER BY posts.date DESC")
    List<Post> findAllRecentPostOfFollowed(@Param("followed_sellers") List<Integer> followed_sellers, @Param("startDate") Date startDate);

    List<Post> findPostsByUser_IdInOrderByDateDesc(List<Integer> followed_sellers);

}
