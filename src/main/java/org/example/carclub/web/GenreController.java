package org.example.carclub.web;

import org.example.carclub.domain.car.CarService;
import org.example.carclub.domain.car.dto.CarDto;
import org.example.carclub.domain.genre.GenreDtoMapper;
import org.example.carclub.domain.genre.GenreService;
import org.example.carclub.domain.genre.dto.GenreDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Controller
public class GenreController {
    private final CarService carService;
    private final GenreService genreService;

    public GenreController(CarService carService, GenreService genreService) {
        this.carService = carService;
        this.genreService = genreService;
    }
    @GetMapping("/gatunek/{name}")
    public String getGenre(@PathVariable String name, Model model){
        GenreDto genre = genreService.findGenreByName(name)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        List<CarDto> cars = carService.findCarByGenreName(name);
        model.addAttribute("heading", genre.getName());
        model.addAttribute("description", genre.getDescription());
        model.addAttribute("cars", cars);
        return "car-listing";
    }
    @GetMapping("/gatunki-aut")
    public String getGenreList(Model model){
        List<GenreDto> allGenres = genreService.findAllGenres();
        model.addAttribute("genres",allGenres);
        return "genre-listing";
    }

}
