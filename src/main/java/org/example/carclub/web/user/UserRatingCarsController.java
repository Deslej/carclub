package org.example.carclub.web.user;

import org.example.carclub.domain.car.CarService;
import org.example.carclub.domain.car.dto.CarDto;
import org.example.carclub.domain.rating.RatingService;
import org.example.carclub.domain.user.UserService;
import org.example.carclub.web.admin.AdminController;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserRatingCarsController {
    private final CarService carService;

    private final UserService userService;
    private final RatingService ratingService;

    public UserRatingCarsController(CarService carService,
                                    UserService userService, RatingService ratingService) {
        this.carService = carService;
        this.userService = userService;
        this.ratingService = ratingService;
    }

    @GetMapping("/user/rating-car")
    public String getUserRatingCars(Model model, Authentication authentication){
        String email = userService.getUserEmail(authentication);
        Long userId = userService.getUserIdByEmail(email);
        List<Long> userRatingsCarsId= ratingService.getAllUserRatings(userId);
        List<CarDto> carDtos=new ArrayList<>();
        //List<Integer> ratingForCars=new ArrayList<>();
        for (Long carId : userRatingsCarsId) {
            carDtos.add(carService.findCarById(carId).orElseThrow());
            //ratingForCars.add(ratingService.getUserRatingForCar(email,carId).orElseThrow());
        }
        model.addAttribute("heading", "Twoje polubione auta");
        model.addAttribute("cars",carDtos);
        //model.addAttribute("carRating",ratingForCars); dodac do html
        return"/user/rating";
    }
    @PostMapping("/user/delete-rating/{id}")
    public String deleteRatingFromCar(@PathVariable long id,Authentication authentication,
                                RedirectAttributes redirectAttributes) {
        String userEmail = userService.getUserEmail(authentication);
        ratingService.deleteByUserAndCarId(userEmail,id);
        redirectAttributes.addFlashAttribute(
                UserController.NOTIFICATION_ATTRIBUTE,
                "usunieto ocene auta o numerze id: %s ".formatted(id));
        return "redirect:/user";

    }

}
