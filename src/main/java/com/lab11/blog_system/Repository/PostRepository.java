package com.lab11.blog_system.Repository;

import com.lab11.blog_system.Model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    Post findPostById(Integer id);
    List<Post> findPostsByUserId(Integer userId);
    List<Post> findPostsByTitle(String title);
    List<Post> findPostsByCategoryId(Integer categoryId);
    @Query("select p from Post p where p.publishDate = ?1")
    List<Post> findPostsByDate(LocalDate date);
}
