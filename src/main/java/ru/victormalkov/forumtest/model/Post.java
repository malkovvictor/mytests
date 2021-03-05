package ru.victormalkov.forumtest.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity(name="posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private User author;

    private String text;

    @Temporal(TemporalType.DATE)
    private Date publicationDate;

    @Temporal(TemporalType.TIME)
    private Date publicationTime;
}
