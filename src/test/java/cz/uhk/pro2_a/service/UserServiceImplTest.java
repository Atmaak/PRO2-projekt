package cz.uhk.pro2_a.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import cz.uhk.pro2_a.model.User;
import cz.uhk.pro2_a.repository.UserRepository;
import cz.uhk.pro2_a.security.MyUserDetails;
import cz.uhk.pro2_a.service.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import java.util.Arrays;
import java.util.Optional;
import java.util.List;

class UserServiceImplTest {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        passwordEncoder = mock(PasswordEncoder.class);
        userService = new UserServiceImpl(userRepository, passwordEncoder);
    }

    @Test
    void testGetAllUsers() {
        List<User> users = Arrays.asList(new User(), new User());
        when(userRepository.findAll()).thenReturn(users);
        assertEquals(users, userService.getAllUsers());
    }

    @Test
    void testSaveUser() {
        User user = new User();
        user.setPassword("plain");
        when(passwordEncoder.encode("plain")).thenReturn("encoded");
        userService.saveUser(user);
        assertEquals("encoded", user.getPassword());
        verify(userRepository).save(user);
    }

    @Test
    void testGetUser() {
        User user = new User();
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        assertEquals(user, userService.getUser(1L));
    }

    @Test
    void testGetUserNotFound() {
        when(userRepository.findById(2L)).thenReturn(Optional.empty());
        assertNull(userService.getUser(2L));
    }

    @Test
    void testDeleteUser() {
        userService.deleteUser(1L);
        verify(userRepository).deleteById(1L);
    }

    @Test
    void testLoadUserByUsername() {
        User user = new User();
        when(userRepository.findByUsername("user")).thenReturn(user);
        assertTrue(userService.loadUserByUsername("user") instanceof MyUserDetails);
    }

    @Test
    void testLoadUserByUsernameNotFound() {
        when(userRepository.findByUsername("nouser")).thenReturn(null);
        assertThrows(UsernameNotFoundException.class, () -> userService.loadUserByUsername("nouser"));
    }
}