package com.demo.demo.mapper;

import com.demo.demo.dto.CategoryDto;
import com.demo.demo.dto.ProductDto;
import com.demo.demo.model.Category;
import com.demo.demo.model.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})

public interface ProductMapper extends EntityMapper<ProductDto, Product>{
    ProductDto toDto(Product product);

    Product toEntity(ProductDto productDto);

    default Product fromId(Long id) {
        if (id == null) {
            return null;
        }
        Product product = new Product();
        product.setId(id);
        return product;
    }
}
