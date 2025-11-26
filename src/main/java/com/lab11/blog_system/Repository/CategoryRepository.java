package com.lab11.blog_system.Repository;

import com.lab11.blog_system.Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Category findCategoryById(Integer id);
    Category findCategoryByName(String name);
}
