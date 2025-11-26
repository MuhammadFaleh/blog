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
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull(message = "user id can't be null")
    @Column(columnDefinition = "int not null")
    private Integer userId;
    @NotNull(message = "post id must not be empty")
    @Column(columnDefinition = "int not null")
    private Integer postId;
    @NotBlank(message = "content can't be empty")
    @Size(min = 10, max = 249, message = "content must be between 10 and 249 in length")
    @Column(columnDefinition = "varchar(249) not null check ( length(content) >=10)")
    private String content;
    @Column(columnDefinition = "datetime not null")
    private LocalDate commentDate;
}
