package com.lab11.blog_system.Repository;

import com.lab11.blog_system.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findUserById(Integer id);
    User findUserByEmail(String email);
    User findUserByUsername(String username);
    @Query("select u from User u where u.registrationDate=?1")
    List<User> findUsersByDate(LocalDate date);
}
