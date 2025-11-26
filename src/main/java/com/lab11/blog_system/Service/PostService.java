package com.lab11.blog_system.Service;

import com.lab11.blog_system.Model.Category;
import com.lab11.blog_system.Model.Comment;
import com.lab11.blog_system.Model.Post;
import com.lab11.blog_system.Repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserService userService;
    private final CategoryService categoryService;

    public List<Post> getPosts(){
        return postRepository.findAll();
    }

    public int createPost(Post post){
        if(categoryService.getCategoryById(post.getCategoryId()) != null){
            if(userService.getUserById(post.getUserId()) != null){
                post.setPublishDate(LocalDate.now());
                postRepository.save(post);
                return 1;
            }
            return -2; // no user
        }
        return -3; // category not found
    }

    public int updatePost(Integer id, Post post){
        Post p = postRepository.findPostById(id);
        if(p != null){
            if(p.getUserId().equals(post.getUserId())){
                if(categoryService.getCategoryById(post.getCategoryId()) != null){
                    p.setTitle(post.getTitle());
                    p.setContent(post.getContent());
                    postRepository.save(p);
                    return 1;
                }
                return -3; //category not found
            }
            return -2; // user not found
        }
        return -1; // post not found
    }

    public int deletePost(Integer id, Integer userId){
        Post p = postRepository.findPostById(id);
        if(p != null){
            if(p.getUserId().equals(userId)){
                postRepository.delete(p);
                return 1;
            }
            return -2;
        }
        return -1;
    }

    public Post getPostById(Integer id){
        return postRepository.findPostById(id);
    }

    public List<Post> getPostsByCategory(String name){
        Category c = categoryService.getCategoryByName(name);
        if(c != null){
            return postRepository.findPostsByCategoryId(categoryService.getCategoryByName(name).getId());
        }
        return postRepository.findPostsByCategoryId(-1);
    }

    public List<Post> getPostsByUser(Integer id){
        return postRepository.findPostsByUserId(id);
    }

    public List<Post> getPostsByTitle(String title){
        return postRepository.findPostsByTitle(title);
    }

    public List<Post> getPostsByDate(LocalDate date){
        return postRepository.findPostsByDate(date);
    }
}
