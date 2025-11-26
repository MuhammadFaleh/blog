package com.lab11.blog_system.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull(message = "category id can't be null")
    @Column(columnDefinition = "int not null")
    private Integer categoryId;
    @NotBlank(message = "title can't be empty")
    @Size(min = 10, max = 200, message = "title must be between 10 and 200 in length")
    @Column(columnDefinition = "varchar(200) not null check(length(title) > 9)")
    private String title;
    @NotBlank(message = "content can't be empty")
    @Size(min = 10, max = 1500, message = "content must be between 10 and 200 in length")
    @Column(columnDefinition = "text not null check ( length(content) <= 1500 and length(content) >=10)")
    private String content;
    @NotNull(message = "user id must not be empty")
    @Column(columnDefinition = "int not null")
    private Integer userId;
    @Column(columnDefinition = "date not null")
    private LocalDate publishDate;

}
