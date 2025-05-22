package com.oyo.oyo.service;

import com.oyo.oyo.dto.LoginDto;
import com.oyo.oyo.dto.PropertyUserDto;
import com.oyo.oyo.entity.PropertyUser;
import com.oyo.oyo.repository.PropertyUserRepository;
import com.oyo.oyo.service.JWTService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    private final JWTService jwtService = mock(JWTService.class);
    private final PropertyUserRepository userRepository = mock(PropertyUserRepository.class);
    private final UserService userService = new UserService(jwtService, userRepository);

    @Test
    void addUserShouldSaveUserWithHashedPassword() {
        PropertyUserDto userDto = new PropertyUserDto();
        userDto.setId(1L);
        userDto.setFirstName("John");
        userDto.setLastName("Doe");
        userDto.setEmail("john.doe@example.com");
        userDto.setUsername("johndoe");
        userDto.setPassword("password123");
        userDto.setUserRole("USER");

        PropertyUser savedUser = new PropertyUser();
        savedUser.setId(1L);
        savedUser.setPassword(BCrypt.hashpw(userDto.getPassword(), BCrypt.gensalt())); // Set hashed password
        when(userRepository.save(any(PropertyUser.class))).thenReturn(savedUser);

        PropertyUser result = userService.addUser(userDto);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(userRepository, times(1)).save(any(PropertyUser.class));
        assertTrue(BCrypt.checkpw("password123", result.getPassword()));
    }

    @Test
    void verifyLoginShouldReturnTokenForValidCredentials() {
        LoginDto loginDto = new LoginDto();
        loginDto.setUsername("johndoe");
        loginDto.setPassword("password123");

        PropertyUser user = new PropertyUser();
        user.setUsername("johndoe");
        user.setPassword(BCrypt.hashpw("password123", BCrypt.gensalt()));

        when(userRepository.findByUsername("johndoe")).thenReturn(Optional.of(user));
        when(jwtService.generateToken(user)).thenReturn("mockedToken");

        String token = userService.verifyLogin(loginDto);

        assertNotNull(token);
        assertEquals("mockedToken", token);
    }

    @Test
    void verifyLoginShouldReturnNullForInvalidPassword() {
        LoginDto loginDto = new LoginDto();
        loginDto.setUsername("johndoe");
        loginDto.setPassword("wrongpassword");

        PropertyUser user = new PropertyUser();
        user.setUsername("johndoe");
        user.setPassword(BCrypt.hashpw("password123", BCrypt.gensalt()));

        when(userRepository.findByUsername("johndoe")).thenReturn(Optional.of(user));

        String token = userService.verifyLogin(loginDto);

        assertNull(token);
    }

    @Test
    void verifyLoginShouldReturnNullForNonExistentUser() {
        LoginDto loginDto = new LoginDto();
        loginDto.setUsername("nonexistent");
        loginDto.setPassword("password123");

        when(userRepository.findByUsername("nonexistent")).thenReturn(Optional.empty());

        String token = userService.verifyLogin(loginDto);

        assertNull(token);
    }
}