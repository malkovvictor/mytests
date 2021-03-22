package ru.victormalkov.forumtest.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.victormalkov.forumtest.model.Post;

import java.util.List;

@Repository
public interface PostRepository extends CrudRepository<Post, Long> {
    List<Post> findAllByOrderByPublicationTimeDesc();
}
