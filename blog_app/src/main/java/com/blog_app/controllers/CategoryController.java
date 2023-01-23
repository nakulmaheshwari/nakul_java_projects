package com.blog_app.controllers;

import com.blog_app.payloads.ApiResponse;
import com.blog_app.payloads.CategoryDto;
import com.blog_app.payloads.UserDto;
import com.blog_app.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/category")
public class CategoryController {

    @Autowired
    private CategoryService cs;

    //create
    @PostMapping("/")
    public ResponseEntity<CategoryDto> createUser(@Valid @RequestBody CategoryDto c)
    {
        CategoryDto cat = this.cs.createCategory(c);
        return new ResponseEntity<>(cat, HttpStatus.CREATED);
    }
    //update
    @PutMapping("/{Id}")
    public ResponseEntity<CategoryDto> updateCat(@Valid  @RequestBody CategoryDto c,@PathVariable("Id") Integer id)
    {
        CategoryDto cat = this.cs.updateCategory(c,id);
        return ResponseEntity.ok(cat);
    }

    //delete
    @DeleteMapping("/{Id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Integer Id)
    {
        this.cs.deleteCategory(Id);
        return new ResponseEntity<ApiResponse>(new ApiResponse("User deleted Successfully", true), HttpStatus.OK);

    }

    //get1
    @GetMapping("/")
    public ResponseEntity<List<CategoryDto>> getAllCategory(){
        return ResponseEntity.ok(this.cs.getAllCategory());
    }

    @GetMapping("/{Id}")
    public ResponseEntity<CategoryDto> getSingleUser(@PathVariable Integer Id){
        return ResponseEntity.ok(this.cs.getCategoryById(Id));
    }

}
