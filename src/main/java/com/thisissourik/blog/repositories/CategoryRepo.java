package com.thisissourik.blog.repositories;

import com.thisissourik.blog.entitites.Category;
import com.thisissourik.blog.payloads.CategoryDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category,Integer> {
}
