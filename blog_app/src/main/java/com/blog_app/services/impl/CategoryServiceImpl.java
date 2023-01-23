package com.blog_app.services.impl;

import com.blog_app.entities.Category;
import com.blog_app.exceptions.ResourceNotFoundException;
import com.blog_app.payloads.CategoryDto;
import com.blog_app.repositories.CategoryRepo;
import com.blog_app.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepo cr;
    @Autowired
    private ModelMapper mm;
    @Override
    public CategoryDto createCategory(CategoryDto c) {
        Category cat = mm.map(c, Category.class);
        Category addedCat = cr.save(cat);

        return mm.map(addedCat, CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto c, Integer id) {
        Category cat = this.cr.findById(id).orElseThrow(()-> new ResourceNotFoundException("User","id",id ));
        cat.setCategoryTitle(c.getCategoryTitle());
        cat.setCategoryDescription(c.getCategoryDescription());
        return mm.map(this.cr.save(cat), CategoryDto.class);
    }

    @Override
    public void deleteCategory(Integer userId) {
        Category cat = this.cr.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id",userId ));
        this.cr.delete(cat);
    }

    @Override
    public CategoryDto getCategoryById(Integer userId) {
        Category cat=this.cr.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id",userId ));
         return mm.map(this.cr.save(cat), CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getAllCategory() {
        List<Category> cat = this.cr.findAll();
        List<CategoryDto> U = cat.stream().map(c -> mm.map(this.cr.save(c), CategoryDto.class)).collect(Collectors.toList());
        return U;
    }
}
