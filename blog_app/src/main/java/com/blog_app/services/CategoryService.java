package com.blog_app.services;

import com.blog_app.payloads.CategoryDto;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CategoryService {
    public CategoryDto createCategory(CategoryDto c);
    //update
    public CategoryDto updateCategory(CategoryDto c,Integer id);
    //delete
    public void deleteCategory(Integer id);
    //get
    public CategoryDto getCategoryById(Integer id);
    //get all
    public List<CategoryDto> getAllCategory();
}
