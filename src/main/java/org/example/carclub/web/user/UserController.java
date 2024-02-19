package org.example.carclub.web.user;

import org.example.carclub.domain.user.UserService;
import org.example.carclub.domain.user.dto.UserCredentialsDto;
import org.example.carclub.web.admin.AdminController;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;



@Controller
public class UserController {
    private final UserService userService;
    public static final String NOTIFICATION_ATTRIBUTE = "notification";

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public String getUserPanel() {
        return "user/user";
    }
    @GetMapping("/user/edit-password")
    public String getUser(Model model, Authentication authentication){
        String userEmail = userService.getUserEmail(authentication);
        UserCredentialsDto loggedUser = userService.findCredentialsByEmail(userEmail)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        model.addAttribute("user",loggedUser);
        return "/user/editPassword-form";
    }
    @PostMapping("/user/edit-password")
    public String editUserPassword(UserCredentialsDto userCredentialsDto
            , RedirectAttributes redirectAttributes){
        userService.editUser(userCredentialsDto);
        redirectAttributes.addFlashAttribute(
                UserController.NOTIFICATION_ATTRIBUTE,
                "Pomyślnie zmieniono informacje o użytkowniku.");
        return "redirect:/user";
    }

}

