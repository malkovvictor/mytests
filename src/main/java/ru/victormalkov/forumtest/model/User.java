package ru.victormalkov.forumtest.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@Entity(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "User name cannot be blank")
    @Column(nullable = false, unique = true)
    private String name;

    private String password;

    private String role;
}
