package com.quiz.service;

import com.quiz.model.User;
import com.quiz.repository.UserRepository;
import com.quiz.service.serviceImpl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

    @Mock
    private UserRepository repository;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveUser() {
        // Test data
        User user = new User();
        when(repository.save(any(User.class))).thenReturn(user);

        // Invoke the method
        User savedUser = userService.save(user);

        // Verify interactions and save method invocation
        assertNotNull(savedUser);
        assertEquals(user, savedUser);
        verify(repository, times(1)).save(any(User.class));
    }

    @Test
    void testFindUserById() {
        // Test data
        Long userId = 1L;
        User user = new User();
        when(repository.findById(anyLong())).thenReturn(Optional.of(user));

        // Invoke the method
        Optional<User> foundUser = userService.findById(userId);

        // Verify the result
        assertTrue(foundUser.isPresent());
        assertEquals(user, foundUser.get());
        verify(repository, times(1)).findById(anyLong());
    }

    @Test
    void testFindUserByIdNotFound() {
        // Test data
        Long userId = 1L;
        when(repository.findById(anyLong())).thenReturn(Optional.empty());

        // Invoke the method
        Optional<User> foundUser = userService.findById(userId);

        // Verify the result
        assertTrue(foundUser.isEmpty());
        verify(repository, times(1)).findById(anyLong());
    }

    @Test
    void testGetAllUsers() {
        // Test data
        User user1 = new User();
        User user2 = new User();
        List<User> userList = Arrays.asList(user1, user2);
        when(repository.findAll()).thenReturn(userList);

        // Invoke the method
        List<User> allUsers = userService.getAll();

        // Verify the result
        assertNotNull(allUsers);
        assertEquals(userList, allUsers);
        verify(repository, times(1)).findAll();
    }
}
