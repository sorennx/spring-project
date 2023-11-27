package com.springproject.managio;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springproject.managio.dto.LoginDTO;
import com.springproject.managio.dto.RegisterDTO;
import com.springproject.managio.model.User;
import com.springproject.managio.permission.Role;
import com.springproject.managio.repository.TokenRepository;
import com.springproject.managio.repository.UserRepository;
import com.springproject.managio.resource.AuthenticationResource;
import com.springproject.managio.service.AuthenticationService;
import com.springproject.managio.service.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthenticationServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private TokenRepository tokenRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtService jwtService;

    @InjectMocks
    private AuthenticationService authenticationService;

    @Test
    public void testRegister() {
        RegisterDTO registerDTO = new RegisterDTO();
        registerDTO.setEmail("test@example.com");
        registerDTO.setPassword("testPassword");
        registerDTO.setRole(Role.ADMIN);

        User user = new User();
        user.setEmail(registerDTO.getEmail());
        user.setPassword(registerDTO.getPassword());
        user.setRole(registerDTO.getRole());

        when(userRepository.save(any(User.class))).thenReturn(user);

        AuthenticationResource authenticationResource = authenticationService.register(registerDTO);
        assertNotNull(authenticationResource);
    }

    @Test
    public void testAuthenticate() {
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setEmail("test@example.com");
        loginDTO.setPassword("testPassword");

        User user = new User();
        user.setEmail(loginDTO.getEmail());
        user.setPassword(loginDTO.getPassword());

        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(user));

        authenticationService.authenticate(loginDTO);

        verify(jwtService, times(1)).generateToken(any(User.class));
    }




}
