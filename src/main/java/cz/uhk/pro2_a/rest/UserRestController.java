package cz.uhk.pro2_a.rest;

import cz.uhk.pro2_a.model.Rating;
import cz.uhk.pro2_a.model.User;
import cz.uhk.pro2_a.service.RatingService;
import cz.uhk.pro2_a.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/users")
public class UserRestController {

    private final UserService userService;
    private final RatingService ratingService;

    @Autowired
    public UserRestController(UserService userService, RatingService ratingService) {
        this.userService = userService;
        this.ratingService = ratingService;
    }

    @GetMapping("/getall")
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/get/{id}")
    public User getUser(@PathVariable long id) {
        return userService.getUser(id);
    }

    @DeleteMapping("/delete/{id}")
    public User deleteUser(@PathVariable long id) {
        User user = userService.getUser(id);
        userService.deleteUser(id);
        return user;
    }

    @PostMapping("/new")
    public User newUser(@RequestBody User user) {
        userService.saveUser(user);
        return user;
    }

    @GetMapping("/ratings/{id}")
    public List<Rating> getRatings(@PathVariable long id){
        return ratingService.getAllRatingsToCourse(id);
    }
}
