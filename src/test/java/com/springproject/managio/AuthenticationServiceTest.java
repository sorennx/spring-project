package com.springproject.managio;

import com.springproject.managio.dto.RegisterDTO;
import com.springproject.managio.model.User;
import com.springproject.managio.permission.Role;
import com.springproject.managio.repository.TokenRepository;
import com.springproject.managio.repository.UserRepository;
import com.springproject.managio.resource.AuthenticationResource;
import com.springproject.managio.service.AuthenticationService;
import com.springproject.managio.service.JwtService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AuthenticationServiceTest {

    @Mock
    private UserRepository userRepository;

    // Actually required mocks
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


}