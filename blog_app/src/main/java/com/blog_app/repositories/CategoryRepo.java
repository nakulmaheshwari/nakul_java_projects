package com.blog_app.repositories;

import com.blog_app.entities.Category;
import com.blog_app.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo  extends JpaRepository<Category,Integer> {
}
