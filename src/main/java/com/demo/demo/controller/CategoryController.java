package com.demo.demo.controller;

import com.demo.demo.dto.CategoryDto;
import com.demo.demo.mapper.CategoryMapper;
import com.demo.demo.model.Category;
import com.demo.demo.service.CategoryService;
import com.demo.demo.utils.HeaderUtil;
import com.demo.demo.utils.MessageCodeUtil;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



import javax.validation.Valid;


@RestController
@RequestMapping("/api/user/category")
@SecurityRequirement(name = "api")
public class CategoryController {
    private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);
    final CategoryService categoryService;
    final CategoryMapper categoryMapper;

    public CategoryController(CategoryService categoryService, CategoryMapper categoryMapper) {
        this.categoryService = categoryService;
        this.categoryMapper = categoryMapper;

    }
    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody CategoryDto categoryDto) {
        try {
            Category request = categoryMapper.toEntity(categoryDto);
            Category category = categoryService.save(request);
            logger.info("category" + category);
            return ResponseEntity.ok().body(categoryMapper.toDto(category));
        } catch (Exception e) {
            HttpHeaders headers = HeaderUtil.createAlert(MessageCodeUtil.AT_001.getErrorCode(),
                    MessageCodeUtil.AT_001.getErrorMessage());
            return ResponseEntity.badRequest().headers(headers).build();
        }
    }
}
