package com.lab11.blog_system.Controller;

import com.lab11.blog_system.Api.ApiResponse;
import com.lab11.blog_system.Model.Category;
import com.lab11.blog_system.Service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/category")
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/get-categories")
    public ResponseEntity<?> getPosts(){
        return ResponseEntity.status(200).body(categoryService.getCategories());
    }

    @PostMapping("/create-category")
    public ResponseEntity<?> createCategory(@RequestBody @Valid Category category, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(new ApiResponse(errors.getFieldError().getDefaultMessage()));
        }
        if(categoryService.createCategory(category)){
            return ResponseEntity.status(200).body(new ApiResponse("category created successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("category already exist"));
    }

    @PutMapping("/update-category/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable Integer id,@RequestBody @Valid Category category, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(new ApiResponse(errors.getFieldError().getDefaultMessage()));
        }
        int status = categoryService.updateCategory(id, category);
        switch (status){
            case 1 ->{return ResponseEntity.status(200).body(new ApiResponse("comment updated successfully"));}
            case -1->{return ResponseEntity.status(400).body(new ApiResponse("category not found"));}
            default->{return ResponseEntity.status(400).body(new ApiResponse("category already exist"));}
        }
    }

    @DeleteMapping("/delete-category/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Integer id){
        if( categoryService.deleteCategory(id)){
            return ResponseEntity.status(200).body(new ApiResponse("category deleted successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("category not found"));
    }
}
