package com.mitrais.test.usermanagementservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mitrais.test.usermanagementservice.dto.UserManagementServiceRequest;
import com.mitrais.test.usermanagementservice.model.User;
import com.mitrais.test.usermanagementservice.service.UserManagementService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(UserManagementServiceController.class)
class UserManagementServiceControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserManagementService userManagementService;

    @BeforeEach
    void setUp() {
    }

    @SneakyThrows
    @Test
    void saveUser() {
        List<User> users = new ArrayList<>();
        users.add(new User(1L, "a", "b", "c", "d", "e", "f", "g", "h"));
        users.add(new User(2L, "b", "c", "d", "e", "f", "g", "h", "a"));
        users.add(new User(3L, "c", "d", "e", "f", "g", "h", "a", "b"));

        UserManagementServiceRequest user = new UserManagementServiceRequest("a", "b", "c", "d", "e", "f", "g", "h");

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                return null;
            }
        }).when(mock(UserManagementService.class)).saveUSer(Mockito.any(User.class));

        mockMvc.perform(post("/saveUser")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(user)))
                .andExpect(status().isOk());
    }

    @SneakyThrows
    @Test
    void findUserByMobileNumberAndEmail() {
        List<User> users = new ArrayList<>();
        users.add(new User(1L, "a", "b", "c", "d", "e", "f", "g", "h"));
        users.add(new User(2L, "b", "c", "d", "e", "f", "g", "h", "a"));
        users.add(new User(3L, "c", "d", "e", "f", "g", "h", "a", "b"));

        UserManagementServiceRequest user = new UserManagementServiceRequest( "a", "b", "c", "d", "e", "f", "g", "h");

        when(userManagementService.findByMobileNumberOrEmail(Mockito.anyString(), Mockito.anyString()))
                .thenReturn(users);

        mockMvc.perform(post("/findUserByMobileNumberAndEmail")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(user)))
        .andExpect(status().isOk());
    }
}