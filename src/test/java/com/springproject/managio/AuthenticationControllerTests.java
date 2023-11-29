package com.springproject.managio;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springproject.managio.dto.LoginDTO;
import com.springproject.managio.dto.RegisterDTO;
import com.springproject.managio.resource.AuthenticationResource;
import com.springproject.managio.service.AuthenticationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthenticationControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthenticationService authenticationService;

    private LoginDTO testLoginDTO;
    private RegisterDTO testRegisterDTO;

    @BeforeEach
    public void setUp() {
        testLoginDTO = new LoginDTO();
        testLoginDTO.setEmail("test@test.com");
        testLoginDTO.setPassword("testPassword");

        testRegisterDTO = new RegisterDTO();
        testRegisterDTO.setFirstname("Test");
        testRegisterDTO.setLastname("Test");
        testRegisterDTO.setEmail("test@test.com");
        testRegisterDTO.setPassword("testPassword");
    }

    @Test
    public void registerTest() throws Exception {

        AuthenticationResource authenticationResource = new AuthenticationResource();
        authenticationResource.setAccessToken("testAccessToken");
        authenticationResource.setRefreshToken("testRefreshToken");

        when(authenticationService.register(any(RegisterDTO.class))).thenReturn(authenticationResource);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(testRegisterDTO)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.access_token").value(authenticationResource.getAccessToken()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.refresh_token").value(authenticationResource.getRefreshToken()));

        verify(authenticationService, times(1)).register(any(RegisterDTO.class));
    }

    @Test
    public void authenticateTest() throws Exception {
        // Arrange
        AuthenticationResource authenticationResource = new AuthenticationResource();
        authenticationResource.setAccessToken("testAccessToken");
        authenticationResource.setRefreshToken("testRefreshToken");

        when(authenticationService.authenticate(any(LoginDTO.class))).thenReturn(authenticationResource);

        // Act and Assert
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/auth/authenticate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(testLoginDTO)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.access_token").value(authenticationResource.getAccessToken()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.refresh_token").value(authenticationResource.getRefreshToken()));

        verify(authenticationService, times(1)).authenticate(any(LoginDTO.class));
    }


}
