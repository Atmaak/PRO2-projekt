package cz.uhk.pro2_a.repository;

import cz.uhk.pro2_a.model.Course;
import cz.uhk.pro2_a.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

}
