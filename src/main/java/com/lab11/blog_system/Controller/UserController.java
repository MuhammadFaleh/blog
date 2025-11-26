package com.lab11.blog_system.Controller;

import com.lab11.blog_system.Api.ApiResponse;
import com.lab11.blog_system.Model.User;
import com.lab11.blog_system.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;

    @GetMapping("/get-users")
    public ResponseEntity<?> getUsers(){
        return ResponseEntity.status(200).body(userService.getUsers());
    }

    @PostMapping("/create-user")
    public ResponseEntity<?> createUser(@RequestBody @Valid User user, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(new ApiResponse(errors.getFieldError().getDefaultMessage()));
        }
        if(userService.createUser(user)){
            return ResponseEntity.status(200).body(new ApiResponse("user created successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("username or email is already used"));
    }

    @PutMapping("/update-user/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Integer id,@RequestBody @Valid User user, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(new ApiResponse(errors.getFieldError().getDefaultMessage()));
        }
        int status = userService.updateUser(id,user);
        if( status == 1){
            return ResponseEntity.status(200).body(new ApiResponse("user updated successfully"));
        }if( status == -1){
            return ResponseEntity.status(400).body(new ApiResponse("username not found"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("username or email is already used"));
    }

    @DeleteMapping("/delete-user/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer id){
        if(userService.delete(id)){
            return ResponseEntity.status(200).body(new ApiResponse("username deleted successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("user not found"));
    }

    @GetMapping("/get-users-date/{date}")
    public ResponseEntity<?> getUserDate(@PathVariable LocalDate date){
        return ResponseEntity.status(200).body(userService.getUsersByDate(date));
    }


}
