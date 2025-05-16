package cz.uhk.pro2_a.repository;

import cz.uhk.pro2_a.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating, Long> {
    List<Rating> findByCourseId(long idCourse);
}
