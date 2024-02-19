package org.example.carclub.web.admin;

import org.example.carclub.domain.rating.RatingService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class RatingController {
    private final RatingService ratingService;

    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @PostMapping("ocen-auto")
    public String addMovieRating (@RequestParam long carId,
                                  @RequestParam int rating,
                                  @RequestHeader String referer,
                                  Authentication authentication) {
        String userEmail = authentication.getName();
        ratingService.addOrUpdateRating(userEmail,carId,rating);
        return "redirect:" + referer;
    }
}
