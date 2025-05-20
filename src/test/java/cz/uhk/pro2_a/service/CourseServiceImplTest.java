package cz.uhk.pro2_a.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import cz.uhk.pro2_a.model.Course;
import cz.uhk.pro2_a.repository.CourseRepository;
import cz.uhk.pro2_a.service.CourseServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Optional;
import java.util.List;

class CourseServiceImplTest {
    private CourseRepository courseRepository;
    private CourseServiceImpl courseService;

    @BeforeEach
    void setUp() {
        courseRepository = mock(CourseRepository.class);
        courseService = new CourseServiceImpl(courseRepository);
    }

    @Test
    void testGetAllCourses() {
        List<Course> courses = Arrays.asList(new Course(), new Course());
        when(courseRepository.findAll()).thenReturn(courses);
        assertEquals(courses, courseService.getAllCourses());
    }

    @Test
    void testSaveCourse() {
        Course course = new Course();
        courseService.saveCourse(course);
        verify(courseRepository).save(course);
    }

    @Test
    void testGetCourse() {
        Course course = new Course();
        when(courseRepository.findById(1L)).thenReturn(Optional.of(course));
        assertEquals(course, courseService.getCourse(1L));
    }

    @Test
    void testGetCourseNotFound() {
        when(courseRepository.findById(2L)).thenReturn(Optional.empty());
        assertNull(courseService.getCourse(2L));
    }

    @Test
    void testDeleteCourse() {
        courseService.deleteCourse(1L);
        verify(courseRepository).deleteById(1L);
    }
}