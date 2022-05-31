package com.demo.demo.service.impl;

import com.demo.demo.model.Product;
import com.demo.demo.repository.ProductRepository;
import com.demo.demo.service.ProductService;
import org.springframework.stereotype.Service;

@Service

public class ProductServicelmpl implements ProductService {
    final ProductRepository repository;

    public ProductServicelmpl(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public Product save(Product product) {
        return repository.save(product);
    }
}
