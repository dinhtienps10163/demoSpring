package com.demo.demo.mapper;

import com.demo.demo.dto.CategoryDto;

import com.demo.demo.model.Category;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface CategoryMapper extends EntityMapper<CategoryDto, Category>{
    CategoryDto toDto(Category category);

    Category toEntity(CategoryDto categoryDto);

    default Category fromId(Long id) {
        if (id == null) {
            return null;
        }
        Category category = new Category();
        category.setId(id);
        return category;
    }
}
