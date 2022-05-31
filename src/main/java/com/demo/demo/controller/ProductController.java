package com.demo.demo.controller;

import com.demo.demo.dto.CategoryDto;
import com.demo.demo.dto.ProductDto;
import com.demo.demo.mapper.ProductMapper;
import com.demo.demo.model.Category;
import com.demo.demo.model.Product;
import com.demo.demo.service.ProductService;
import com.demo.demo.utils.HeaderUtil;
import com.demo.demo.utils.MessageCodeUtil;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
@RestController
@RequestMapping("/api/user/product")
@SecurityRequirement(name = "api")
public class ProductController {
    private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);
    @Autowired
    final ProductService productService;
    @Autowired
    final ProductMapper productMapper;

    public ProductController(ProductService productService, ProductMapper productMapper) {
        this.productService = productService;
        this.productMapper = productMapper;
    }


    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody ProductDto productDto) {
        try {
            Product request = productMapper.toEntity(productDto);
            Product product = productService.save(request);
            logger.info("product" + product);
            return ResponseEntity.ok().body(productMapper.toDto(product));
        } catch (Exception e) {
            HttpHeaders headers = HeaderUtil.createAlert(MessageCodeUtil.AT_001.getErrorCode(),
                    MessageCodeUtil.AT_001.getErrorMessage());
            return ResponseEntity.badRequest().headers(headers).build();
        }
    }
}
