package ru.victormalkov.forumtest;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import ru.victormalkov.forumtest.dto.UserDTO;
import ru.victormalkov.forumtest.model.User;
import ru.victormalkov.forumtest.services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Slf4j
@Controller
public class RegistrationController {
    @Autowired
    UserService userService;

    @GetMapping("/registration")
    public String showRegistrationForm(WebRequest request, Model model) {
        final UserDTO userDTO = new UserDTO();
        model.addAttribute("user", userDTO);
        return "register";
    }

    @PostMapping("/registration")
    public String addUser(
            @ModelAttribute("user") @Valid UserDTO userDto,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.debug("binding result: {}", bindingResult);
            return "register";
        }
            //try {
                User registered = userService.registerNewAccount(userDto);
            //} catch (UserAlreadyExistException uaeEx) {
//                mav.addObject("message", "An account for that username/email already exists.");
  //              return mav;
    //        }

            return "login";
    }

}
