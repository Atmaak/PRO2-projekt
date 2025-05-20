package cz.uhk.pro2_a.controller;

import cz.uhk.pro2_a.model.Rating;
import cz.uhk.pro2_a.security.MyUserDetails;
import cz.uhk.pro2_a.service.CourseService;
import cz.uhk.pro2_a.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rating")
public class RatingController {

    private final RatingService ratingService;
    private final CourseService courseService;

    @Autowired
    public RatingController(RatingService ratingService, CourseService courseService) {
        this.ratingService = ratingService;
        this.courseService = courseService;
    }

    @PostMapping("/save/{courseId}")
    public ResponseEntity<Rating> saveRating(@RequestBody Rating rating, @PathVariable long courseId, Authentication authentication) {
        MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();
        rating.setUser(userDetails.getUser());
        rating.setCourse(courseService.getCourse(courseId));
        ratingService.saveRating(rating);
        return ResponseEntity.ok(rating);
    }

    @GetMapping("/{courseId}")
    public List<Rating> getRatingsOnCourse(@PathVariable long courseId){
        List<Rating> ratings = ratingService.getAllRatingsToCourse(courseId);
        java.util.Collections.reverse(ratings);
        return ratings;
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable long id) {
        ratingService.deleteRating(id);
        return "redirect:/courses/";
    }

}
