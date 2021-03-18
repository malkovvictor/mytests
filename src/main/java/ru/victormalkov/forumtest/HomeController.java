package ru.victormalkov.forumtest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.victormalkov.forumtest.dto.PostDTO;
import ru.victormalkov.forumtest.model.Post;
import ru.victormalkov.forumtest.repository.PostRepository;
import ru.victormalkov.forumtest.services.PostService;

import javax.validation.Valid;

@Controller
public class HomeController {
    @Autowired
    PostRepository postRepository;

    @Autowired
    PostService postService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("posts", postRepository.findAll());
        PostDTO postDTO = new PostDTO();
        model.addAttribute("newpost", postDTO);
        return "home";
    }

    @PostMapping("/post")
    public String newPost(@Valid @ModelAttribute("newpost") PostDTO postDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            // TODO: binding errors in new post
        }

        postService.newPost(postDTO);
        return "redirect:/";
    }
}
