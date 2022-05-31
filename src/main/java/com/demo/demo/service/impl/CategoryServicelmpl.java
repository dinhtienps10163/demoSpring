package com.demo.demo.service.impl;


import com.demo.demo.model.Category;
import com.demo.demo.model.Users;
import com.demo.demo.repository.CategoryRepository;
import com.demo.demo.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryServicelmpl implements CategoryService {
    final CategoryRepository categoryRepository;

    public CategoryServicelmpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

}
