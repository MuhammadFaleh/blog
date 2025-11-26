package com.lab11.blog_system.Service;

import com.lab11.blog_system.Model.Comment;
import com.lab11.blog_system.Repository.CommentRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserService userService;
    private final PostService postService;

    public List<Comment> getComments(){
        return commentRepository.findAll();
    }

    public int createComment(Comment comment){
        if(postService.getPostById(comment.getPostId()) != null){
            if(userService.getUserById(comment.getUserId()) != null){
                comment.setCommentDate(LocalDate.now());
                commentRepository.save(comment);
                return 1;
            }
            return -2; //user not found
        }
        return -1; // post not found

    }

    public int updateComment(Integer id, Comment comment){
        Comment c = getCommentById(id);
        if(c != null){
            if(c.getUserId().equals(comment.getUserId())){
                if(c.getPostId().equals(comment.getPostId())){
                    c.setContent(comment.getContent());
                    commentRepository.save(c);
                }
                return -1; // post not found
            }
            return -2; // user not found
        }
        return -3; //comment not found
    }

    public int deleteComment(Integer id, Integer userId){
        Comment c = getCommentById(id);
        if(c!=null){
            if(userId.equals(c.getUserId()) || userId.equals(postService.getPostById(c.getPostId()).getUserId())){
                commentRepository.delete(c);
            }
            return -4; // unauthorized
        }
        return -3;
    }

    public Comment getCommentById(Integer id){
        return commentRepository.findCommentById(id);
    }

    public List<Comment> getCommentsByPostId(Integer id){
        return commentRepository.findCommentsByPostId(id);
    }


    public List<Comment> getCommentsByUserId(Integer id){
        return commentRepository.findCommentsByUserId(id);
    }

    public List<Comment> getCommentsByDate(LocalDate date1){
        return commentRepository.findCommentsByDate(date1);
    }
}
