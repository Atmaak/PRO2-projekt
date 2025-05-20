package cz.uhk.pro2_a.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import cz.uhk.pro2_a.model.Lecturer;
import cz.uhk.pro2_a.repository.LecturerRepository;
import cz.uhk.pro2_a.service.LecturerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Optional;
import java.util.List;

class LecturerServiceImplTest {
    private LecturerRepository lecturerRepository;
    private LecturerServiceImpl lecturerService;

    @BeforeEach
    void setUp() {
        lecturerRepository = mock(LecturerRepository.class);
        lecturerService = new LecturerServiceImpl(lecturerRepository);
    }

    @Test
    void testGetAllLecturers() {
        List<Lecturer> lecturers = Arrays.asList(new Lecturer(), new Lecturer());
        when(lecturerRepository.findAll()).thenReturn(lecturers);
        assertEquals(lecturers, lecturerService.getAllLecturers());
    }

    @Test
    void testSaveLecturer() {
        Lecturer lecturer = new Lecturer();
        lecturerService.saveLecturer(lecturer);
        verify(lecturerRepository).save(lecturer);
    }

    @Test
    void testGetLecturer() {
        Lecturer lecturer = new Lecturer();
        when(lecturerRepository.findById(1L)).thenReturn(Optional.of(lecturer));
        assertEquals(lecturer, lecturerService.getLecturer(1L));
    }

    @Test
    void testGetLecturerNotFound() {
        when(lecturerRepository.findById(2L)).thenReturn(Optional.empty());
        assertNull(lecturerService.getLecturer(2L));
    }

    @Test
    void testDeleteLecturer() {
        lecturerService.deleteLecturer(1L);
        verify(lecturerRepository).deleteById(1L);
    }
}