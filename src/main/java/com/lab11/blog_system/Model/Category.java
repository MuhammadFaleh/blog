package com.lab11.blog_system.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank(message = "name must not be empty")
    @Size(max = 50, message = "category can't be longer than 50 characters long")
    @Column(columnDefinition = "varchar(50) not null unique")
    private String name;
}
