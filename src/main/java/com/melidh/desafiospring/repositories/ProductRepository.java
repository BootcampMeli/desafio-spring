package com.melidh.desafiospring.repositories;

import com.melidh.desafiospring.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
