package com.mitrais.test.userservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mitrais.test.userservice.constant.ResponseCode;
import com.mitrais.test.userservice.constant.ResponseMessage;
import com.mitrais.test.userservice.dto.UserServiceRequest;
import com.mitrais.test.userservice.dto.UserServiceResponse;
import com.mitrais.test.userservice.service.UserService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserServiceController.class)
class UserServiceControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserService userService;

    @BeforeEach
    void setUp() {
    }

    @SneakyThrows
    @Test
    void register() {
        UserServiceRequest userServiceRequest = new UserServiceRequest( "a", "b", "c", "d", "e", "f", "g", "h");

        when(userService.register(Mockito.any(UserServiceRequest.class)))
                .thenReturn(UserServiceResponse.builder()
                        .responseCode(ResponseCode.SUCCESS)
                        .responseMessage(ResponseMessage.SUCCESS)
                        .build());

        mockMvc.perform(post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(userServiceRequest)))
                .andExpect(status().isOk());
    }
}