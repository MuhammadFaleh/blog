package com.lab11.blog_system.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank(message = "username can't be empty")
    @Size(message = "size must be at least 4 characters long and 40 max length", min = 4, max = 40)
    @Column(columnDefinition = "varchar(40) not null unique check(length(username)>3)")
    private String username;
    @NotBlank(message = "password can't be empty")
    @Size(message = "password must be at least 5 characters long and not longer than 40", min = 5, max = 40)
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$&+,:;=?@#|'<>.^*()%!-]).*$",
            message = "please enter at least one number and special character")
    @Column(columnDefinition = "varchar(40) not null check(length(password) > 4)")
    private String password;
    @NotBlank(message = "email can't be empty")
    @Email(message = "enter a valid email")
    @Size(max = 200, message = "email size can't be longer than 200 characters")
    @Column(columnDefinition = "varchar(200) not null unique")
    private String email;
    @Column(columnDefinition = "date not null")
    private LocalDate registrationDate;
}
