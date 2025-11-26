package com.lab11.blog_system.Service;

import com.lab11.blog_system.Model.Category;
import com.lab11.blog_system.Repository.CategoryRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public List<Category> getCategories(){
        return categoryRepository.findAll();
    }

    public boolean createCategory(Category category){
        if(getCategoryByName(category.getName()) !=null){
            return false;
        }
        categoryRepository.save(category);
        return true;
    }

    public int updateCategory(Integer id, Category category){
        Category c = getCategoryById(id);
        if(c != null){
            if(getCategoryByName(category.getName()) == null){
                c.setName(category.getName());
                categoryRepository.save(c);
                return 1;
            }else return -2; // category already exist

        }
        return -1; // category not found
    }

    public boolean deleteCategory(Integer id){
        Category c = getCategoryById(id);
        if(c != null){
            categoryRepository.delete(c);
            return true;
        }
        return false;
    }

    public Category getCategoryById(Integer id){
        return categoryRepository.findCategoryById(id);
    }

    public Category getCategoryByName(String name){
        return categoryRepository.findCategoryByName(name);
    }
}
