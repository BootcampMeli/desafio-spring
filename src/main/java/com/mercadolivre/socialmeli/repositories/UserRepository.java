package com.mercadolivre.socialmeli.repositories;

import com.mercadolivre.socialmeli.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
