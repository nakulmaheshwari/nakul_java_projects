package com.blog_app.repositories;
import com.blog_app.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo  extends JpaRepository<Role, Integer>{

}