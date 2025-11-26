package com.lab11.blog_system.Controller;

import com.lab11.blog_system.Api.ApiResponse;
import com.lab11.blog_system.Model.Comment;
import com.lab11.blog_system.Service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/comment")
public class CommentController {
    private final CommentService commentService;

    @GetMapping("/get-comments")
    public ResponseEntity<?> getPosts(){
        return ResponseEntity.status(200).body(commentService.getComments());
    }

    @PostMapping("/create-comment")
    public ResponseEntity<?> createComment(@RequestBody @Valid Comment comment, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(new ApiResponse(errors.getFieldError().getDefaultMessage()));
        }
        int status = commentService.createComment(comment);
        switch (status){
            case 1 ->{return ResponseEntity.status(200).body(new ApiResponse("comment created successfully"));}
            case -2->{return ResponseEntity.status(400).body(new ApiResponse("user not found"));}
            default->{return ResponseEntity.status(400).body(new ApiResponse("post not found"));}
        }
    }

    @PutMapping("/update-comment/{id}")
    public ResponseEntity<?> updatePost(@PathVariable Integer id,@RequestBody @Valid Comment comment, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(new ApiResponse(errors.getFieldError().getDefaultMessage()));
        }
        int status = commentService.updateComment(id, comment);
        switch (status){
            case 1 ->{return ResponseEntity.status(200).body(new ApiResponse("comment updated successfully"));}
            case -1->{return ResponseEntity.status(400).body(new ApiResponse("post not found"));}
            case -2->{return ResponseEntity.status(400).body(new ApiResponse("user not found"));}
            default->{return ResponseEntity.status(400).body(new ApiResponse("comment not found"));}
        }
    }

    @DeleteMapping("/delete-comment/{id}/{userId}")
    public ResponseEntity<?> deletePost(@PathVariable Integer id, @PathVariable Integer userId){
        int status = commentService.deleteComment(id, userId);
        if( status == 1){
            return ResponseEntity.status(200).body(new ApiResponse("comment deleted successfully"));
        }else if(status == -4) {
            return ResponseEntity.status(400).body(new ApiResponse("user not authorized"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("comment not found"));
    }

    @GetMapping("/get-comment-date/{date1}")
    public ResponseEntity<?> getCommentDate(@PathVariable LocalDate date1){
        return ResponseEntity.status(200).body(commentService.getCommentsByDate(date1));
    }

    @GetMapping("/get-comment-post/{postId}")
    public ResponseEntity<?> getCommentPost(@PathVariable Integer postId){
        return ResponseEntity.status(200).body(commentService.getCommentsByPostId(postId));
    }

    @GetMapping("/get-comment-user/{userId}")
    public ResponseEntity<?> getPostTitle(@PathVariable Integer userId){
        return ResponseEntity.status(200).body(commentService.getCommentsByUserId(userId));
    }

}
