package ru.victormalkov.forumtest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.victormalkov.forumtest.dto.PostDTO;
import ru.victormalkov.forumtest.model.Post;
import ru.victormalkov.forumtest.repository.PostRepository;
import ru.victormalkov.forumtest.security.MyUserDetails;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    PostRepository postRepository;

    @Override
    public Post newPost(PostDTO postDTO) {
        Post post = new Post();
        post.setText(postDTO.getText());
        postRepository.save(post);
        return post;
    }
}
