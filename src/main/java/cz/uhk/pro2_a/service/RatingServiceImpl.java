package cz.uhk.pro2_a.service;

import cz.uhk.pro2_a.model.Rating;
import cz.uhk.pro2_a.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingServiceImpl implements RatingService {

    private final RatingRepository ratingRepository;

    @Autowired
    public RatingServiceImpl(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    @Override
    public List<Rating> getAllRatingsToCourse(long idCourse) {
        return ratingRepository.findByCourseId(idCourse);
    }

    @Override
    public Rating getRating(long id) {
        return null;
    }

    @Override
    public void saveRating(Rating rating) {
        ratingRepository.save(rating);
    }

    @Override
    public Rating deleteRating(long id) {
        return null;
    }
}
