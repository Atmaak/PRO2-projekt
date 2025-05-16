package cz.uhk.pro2_a.service;

import cz.uhk.pro2_a.model.Rating;

import java.util.List;

public interface RatingService {
    List<Rating> getAllRatingsToCourse(long idCourse);
    Rating getRating(long id);
    void saveRating(Rating rating);
    Rating deleteRating(long id);
}
