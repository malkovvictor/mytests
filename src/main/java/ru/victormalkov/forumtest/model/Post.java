package ru.victormalkov.forumtest.model;

import javax.persistence.*;

@Entity
public class Post {
    @Id
    @GeneratedValue
    private long id;

    @JoinColumn(name = "userId")
    private User author;

    private String text;
}
