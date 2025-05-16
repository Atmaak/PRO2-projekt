package cz.uhk.pro2_a.controller;

import cz.uhk.pro2_a.model.Rating;
import cz.uhk.pro2_a.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rating")
public class RatingController {

    private final RatingService ratingService;

    @Autowired
    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @PostMapping("/save")
    public void saveRating(@RequestBody Rating rating) {
        ratingService.saveRating(rating);
    }

}
