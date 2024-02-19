package org.example.carclub.web.admin;

import org.example.carclub.domain.car.CarService;
import org.example.carclub.domain.car.dto.CarDto;
import org.example.carclub.domain.car.dto.CarSaveDto;
import org.example.carclub.domain.genre.GenreService;
import org.example.carclub.domain.genre.dto.GenreDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class CarManagmentController {
    private final CarService carService;
    private final GenreService genreService;

    public CarManagmentController(CarService carService, GenreService genreService) {
        this.carService = carService;
        this.genreService = genreService;
    }
    @GetMapping("/admin/dodaj-auto")
    public String addCarForm(Model model){
        List<GenreDto> allGenres = genreService.findAllGenres();
        model.addAttribute("genres", allGenres);
        CarSaveDto carDto = new CarSaveDto();
        model.addAttribute("car", carDto);
        return "admin/add/car-form";
    }
    @PostMapping("/admin/dodaj-auto")
    public String addCar(CarSaveDto carSaveDto, RedirectAttributes redirectAttributes){
        carService.addCar(carSaveDto);
        redirectAttributes.addFlashAttribute(
                AdminController.NOTIFICATION_ATTRIBUTE,
                "Auto %s zostało zapisane".formatted(carSaveDto.getBrand()));
        return "redirect:/admin";
    }
    @GetMapping("/admin/edytuj-auto")
    public String editCar(Model model){
        List<CarDto> allCars = carService.findAllCars();
        model.addAttribute("heading", "Edycja auta");
        model.addAttribute("cars", allCars);
        List<GenreDto> allGenres = genreService.findAllGenres();
        model.addAttribute("genres", allGenres);
        return "admin/edit-car/edit-listing";
    }

    @GetMapping("/admin/edytuj-auto/{id}")
    public String editCarForm(@PathVariable long id,Model model){
        CarDto carDto = carService.findCarById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        List<GenreDto> allGenres = genreService.findAllGenres();
        MultipartFile multipartFile =new CarSaveDto().getPoster();
        model.addAttribute("car",carDto);
        model.addAttribute("genres", allGenres);
        model.addAttribute("MultipartFile", multipartFile);
        return "admin/edit-car/edit-form";
    }

    @PostMapping("/admin/edytuj-auto/{id}")
    public String editCarForm(CarDto carDto,MultipartFile multipartFile,
                            RedirectAttributes redirectAttributes) {
        carService.updateCar(carDto,multipartFile);
        redirectAttributes.addFlashAttribute(
                AdminController.NOTIFICATION_ATTRIBUTE,
                "Edycja auta %s zostało zapisane".formatted(carDto.getBrand()));
        return "redirect:/admin";
    }
    @GetMapping("/admin/delete-car")
    public String deleteCarForm(Model model){
        List<CarDto> allCars = carService.findAllCars();
        model.addAttribute("heading", "Usuwanie aut");
        model.addAttribute("cars", allCars);
        return "admin/edit-car/delete-form";
    }
    @PostMapping("/admin/delete-car/{id}")
    public String deleteCarForm(@PathVariable long id,
                              RedirectAttributes redirectAttributes) {
        carService.deleteCarById(id);
        redirectAttributes.addFlashAttribute(
                AdminController.NOTIFICATION_ATTRIBUTE,
                "usunieto auto o numerze id: %s ".formatted(id));
        return "redirect:/admin";
    }



}
