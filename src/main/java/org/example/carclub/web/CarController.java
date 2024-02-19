package org.example.carclub.web;

import org.example.carclub.domain.car.CarService;
import org.example.carclub.domain.car.dto.CarDto;
import org.example.carclub.domain.rating.RatingService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
@Controller
public class CarController {
    private final CarService carService;
    private final RatingService ratingService;

    public CarController(CarService carService, RatingService ratingService) {
        this.carService = carService;
        this.ratingService = ratingService;
    }
    @GetMapping("/auto/{id}")
    public String getCar(@PathVariable long id, Model model, Authentication authentication){
        CarDto carDto = carService.findCarById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        model.addAttribute("car",carDto);
        if(authentication!=null){
            String userEmail = authentication.getName();
            Integer rating = ratingService.getUserRatingForCar(userEmail, id).orElse(0);
            model.addAttribute("userRating", rating);
        }
        return "cars";
    }
    @GetMapping("/top10")
    public String findTop10(Model model){
        List<CarDto> top10Cars = carService.findTopCars(10);
        model.addAttribute("heading", "Auta TOP10");
        model.addAttribute("description", "Auta najlepiej oceniane przez użytkowników");
        model.addAttribute("cars", top10Cars);
        return "car-listing";
    }

}
