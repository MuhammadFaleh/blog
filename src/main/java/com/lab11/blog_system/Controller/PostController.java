package com.lab11.blog_system.Controller;

import com.lab11.blog_system.Api.ApiResponse;
import com.lab11.blog_system.Model.Post;
import com.lab11.blog_system.Service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/post")
public class PostController {
    private final PostService postService;

    @GetMapping("/get-posts")
    public ResponseEntity<?> getPosts(){
        return ResponseEntity.status(200).body(postService.getPosts());
    }

    @PostMapping("/create-post")
    public ResponseEntity<?> createPost(@RequestBody @Valid Post post, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(new ApiResponse(errors.getFieldError().getDefaultMessage()));
        }
        int status = postService.createPost(post);
        switch (status){
            case 1 ->{return ResponseEntity.status(200).body(new ApiResponse("post created successfully"));}
            case -2->{return ResponseEntity.status(400).body(new ApiResponse("user not found"));}
            default->{return ResponseEntity.status(400).body(new ApiResponse("category not found"));}
        }
    }

    @PutMapping("/update-post/{id}")
    public ResponseEntity<?> updatePost(@PathVariable Integer id,@RequestBody @Valid Post post, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(new ApiResponse(errors.getFieldError().getDefaultMessage()));
        }
        int status = postService.updatePost(id, post);
        switch (status){
            case 1 ->{return ResponseEntity.status(200).body(new ApiResponse("post updated successfully"));}
            case -1->{return ResponseEntity.status(400).body(new ApiResponse("post not found"));}
            case -2->{return ResponseEntity.status(400).body(new ApiResponse("user not found"));}
            default->{return ResponseEntity.status(400).body(new ApiResponse("category not found"));}
        }
    }

    @DeleteMapping("/delete-post/{id}/{userId}")
    public ResponseEntity<?> deletePost(@PathVariable Integer id, @PathVariable Integer userId){
        int status = postService.deletePost(id,userId);
        if( status == 1){
            return ResponseEntity.status(200).body(new ApiResponse("post deleted successfully"));
        }else if(status == -1) {

            return ResponseEntity.status(400).body(new ApiResponse("post not found"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("user not authorized"));
    }

    @GetMapping("/get-post-date/{date1}")
    public ResponseEntity<?> getPostDate(@PathVariable LocalDate date1){
        return ResponseEntity.status(200).body(postService.getPostsByDate(date1));
    }

    @GetMapping("/get-post-category/{category}")
    public ResponseEntity<?> getPostCategory(@PathVariable String category){
        return ResponseEntity.status(200).body(postService.getPostsByCategory(category));
    }

    @GetMapping("/get-post-title/{title}")
    public ResponseEntity<?> getPostTitle(@PathVariable String title){
        return ResponseEntity.status(200).body(postService.getPostsByTitle(title));
    }

    @GetMapping("/get-post-user/{id}")
    public ResponseEntity<?> getPostUser(@PathVariable Integer id){
        return ResponseEntity.status(200).body(postService.getPostsByUser(id));
    }



}
