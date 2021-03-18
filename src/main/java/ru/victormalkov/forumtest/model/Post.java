package ru.victormalkov.forumtest.model;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.lang.Nullable;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.victormalkov.forumtest.security.MyUserDetails;

import javax.persistence.*;
import java.time.Instant;

@Slf4j
@Data
@Entity(name="posts")
@EntityListeners(AuditingEntityListener.class)
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @CreatedBy
    @ManyToOne
    private User author;

    private String text;

    @CreatedDate
    private Instant publicationTime;

    @Nullable
    private Boolean deleted = false;
}
