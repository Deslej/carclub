package org.example.carclub.web.admin;

import org.example.carclub.domain.user.UserService;
import org.example.carclub.domain.user.dto.UserRegistrationDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {
    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/registration")
    public String registrationForm(Model model){
        UserRegistrationDto userRegistrationDto=new UserRegistrationDto();
        model.addAttribute("user",userRegistrationDto);
        return "registration-form";
    }

    @PostMapping("/registration")
    public String registration(UserRegistrationDto userRegistrationDto){
        userService.registerUserWithDefaultRole(userRegistrationDto);
        return "redirect:/login";
    }
}
