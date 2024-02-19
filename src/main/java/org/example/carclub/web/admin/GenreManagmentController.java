package org.example.carclub.web.admin;

import org.example.carclub.domain.genre.GenreService;
import org.example.carclub.domain.genre.dto.GenreDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class GenreManagmentController {
    private final GenreService genreService;

    public GenreManagmentController(GenreService genreService) {
        this.genreService = genreService;
    }
    @GetMapping("/admin/dodaj-gatunek")
    public String addGenreForm(Model model){
        GenreDto genreDto = new GenreDto();
        model.addAttribute("genre", genreDto);
        return "admin/add/genre-form";
    }
    @PostMapping("/admin/dodaj-gatunek")
    public String addGenre(GenreDto genre, RedirectAttributes redirectAttributes) {
        genreService.addGenre(genre);
        redirectAttributes.addFlashAttribute(
                AdminController.NOTIFICATION_ATTRIBUTE,
                "Gatunek %s został zapisany".formatted(genre.getName()));
        return "redirect:/admin";
    }

}
