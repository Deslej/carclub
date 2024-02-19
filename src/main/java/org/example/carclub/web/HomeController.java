package org.example.carclub.web;

import org.example.carclub.domain.car.CarService;
import org.example.carclub.domain.car.dto.CarDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@Controller
public class HomeController {
    private final CarService carService;

    public HomeController(CarService carService) {
        this.carService = carService;
    }


    @GetMapping("/")
    public String home(Model model) {
        List<CarDto> promotedCar = carService.findAllPromotedCars();
        model.addAttribute("heading", "Promowane auta");
        model.addAttribute("description", "Auta polecane przez nasz zespół");
        model.addAttribute("cars", promotedCar);
        return "car-listing";
    }

}