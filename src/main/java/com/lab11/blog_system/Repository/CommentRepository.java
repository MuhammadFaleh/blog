package com.lab11.blog_system.Repository;

import com.lab11.blog_system.Model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    Comment findCommentById(Integer id);
    List<Comment> findCommentsByPostId(Integer postId);
    List<Comment> findCommentsByUserId(Integer userId);
    @Query("Select d from Comment d where d.commentDate=?1")
    List<Comment> findCommentsByDate(LocalDate date);
}
