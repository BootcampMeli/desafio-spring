package com.mercadolivre.socialmeli.repositories;

import com.mercadolivre.socialmeli.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {
}
