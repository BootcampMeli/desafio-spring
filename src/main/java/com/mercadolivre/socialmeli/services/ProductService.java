package com.mercadolivre.socialmeli.services;

import com.mercadolivre.socialmeli.repositories.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository repository;

    public ProductService (ProductRepository repository){
        this.repository = repository;
    }
}
