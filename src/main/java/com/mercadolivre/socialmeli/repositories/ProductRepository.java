package com.mercadolivre.socialmeli.repositories;

import com.mercadolivre.socialmeli.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
