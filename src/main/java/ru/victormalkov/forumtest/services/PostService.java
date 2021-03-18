package ru.victormalkov.forumtest.services;

import ru.victormalkov.forumtest.dto.PostDTO;
import ru.victormalkov.forumtest.model.Post;

public interface PostService {
    Post newPost(PostDTO postDTO);
}
