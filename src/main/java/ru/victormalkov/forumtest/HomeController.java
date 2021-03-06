package ru.victormalkov.forumtest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;
import ru.victormalkov.forumtest.dto.UserDTO;
import ru.victormalkov.forumtest.repository.PostRepository;

@Controller
public class HomeController {
    @Autowired
    PostRepository postRepository;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("posts", postRepository.findAll());
        return "home";
    }

    @GetMapping("/registration")
    public String showRegistrationForm(WebRequest request, Model model) {
        UserDTO userDTO = new UserDTO();
        model.addAttribute("user", userDTO);
        return "register";
    }

    @PostMapping("/registration")
    public String addUser(UserDTO userDTO, BindingResult br, Model model) {
        return "";
    }
}
