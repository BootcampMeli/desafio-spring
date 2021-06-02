package br.com.socialmeli.model.repository;

import br.com.socialmeli.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
