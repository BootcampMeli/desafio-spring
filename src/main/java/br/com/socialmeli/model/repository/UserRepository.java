package br.com.socialmeli.model.repository;

import br.com.socialmeli.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
