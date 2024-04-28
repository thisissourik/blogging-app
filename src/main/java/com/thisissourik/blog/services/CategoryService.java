package com.thisissourik.blog.services;

import com.thisissourik.blog.payloads.CategoryDto;

import java.util.List;

public interface CategoryService {
    //create
    CategoryDto createCategory(CategoryDto categoryDto);

    //update
    CategoryDto updateCategory(CategoryDto categoryDto,int categoryId);

    //delete
    void deleteCategory(int categoryId);

    //get
    CategoryDto getCategory(int categoryId);

    //get all
    List<CategoryDto> getCategories();
}
