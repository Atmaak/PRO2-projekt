package cz.uhk.pro2_a.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import cz.uhk.pro2_a.model.Rating;
import cz.uhk.pro2_a.repository.RatingRepository;
import cz.uhk.pro2_a.service.RatingServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

class RatingServiceImplTest {
    private RatingRepository ratingRepository;
    private RatingServiceImpl ratingService;

    @BeforeEach
    void setUp() {
        ratingRepository = mock(RatingRepository.class);
        ratingService = new RatingServiceImpl(ratingRepository);
    }

    @Test
    void testGetAllRatingsToCourse() {
        List<Rating> ratings = Arrays.asList(new Rating(), new Rating());
        when(ratingRepository.findByCourseId(1L)).thenReturn(ratings);
        assertEquals(ratings, ratingService.getAllRatingsToCourse(1L));
    }

    @Test
    void testSaveRating() {
        Rating rating = new Rating();
        ratingService.saveRating(rating);
        verify(ratingRepository).save(rating);
    }

    @Test
    void testDeleteRating() {
        ratingService.deleteRating(1L);
        verify(ratingRepository).deleteById(1L);
    }
}