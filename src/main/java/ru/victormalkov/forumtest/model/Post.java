package ru.victormalkov.forumtest.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name="posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private User author;

    private String text;
}
