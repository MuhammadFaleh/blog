package com.lab11.blog_system.Service;

import com.lab11.blog_system.Model.User;
import com.lab11.blog_system.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public boolean createUser(User user){
    if(getUserByEmail(user.getEmail()) != null || getUserByUsername(user.getUsername()) !=null){
        return false; // username or email exists
    }
        user.setRegistrationDate(LocalDate.now());
        userRepository.save(user);
        return true;
    }

    public int updateUser(Integer id, User user){
        User u = getUserById(id);
        if(u !=null){
            if(u.getEmail().equalsIgnoreCase(user.getEmail()) || getUserByEmail(user.getEmail()) == null ){
                if(u.getUsername().equalsIgnoreCase(user.getUsername()) || getUserByUsername(user.getUsername()) == null){
                    u.setUsername(user.getUsername());
                    u.setEmail(user.getEmail());
                    u.setPassword(user.getPassword());
                    u.setRegistrationDate(LocalDate.now());
                    userRepository.save(u);
                    return 1;
                }
                return -2; //username or email not available
            }
            return -2; //username or email not available
        }
        return -1; // no user found

    }

    public boolean delete(Integer id){
        User user = getUserById(id);
        if(user != null){
            userRepository.delete(user);
            return true;
        }
        return false;
    }

    public List<User> getUsersByDate(LocalDate date){
        return userRepository.findUsersByDate(date);
    }

    public User getUserById(Integer id){
        return userRepository.findUserById(id);
    }

    public User getUserByEmail(String email){
        return userRepository.findUserByEmail(email);
    }

    public User getUserByUsername(String username){
        return userRepository.findUserByUsername(username);
    }
}
