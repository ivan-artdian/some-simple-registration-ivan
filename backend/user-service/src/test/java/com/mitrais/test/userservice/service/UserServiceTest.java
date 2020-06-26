package com.mitrais.test.userservice.service;

import com.mitrais.test.userservice.constant.ResponseCode;
import com.mitrais.test.userservice.constant.ResponseMessage;
import com.mitrais.test.userservice.dto.UserManagementServiceResponse;
import com.mitrais.test.userservice.dto.UserServiceRequest;
import com.mitrais.test.userservice.dto.UserServiceResponse;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserService.class)
class UserServiceTest {
    @Autowired
    private UserService userService;
    @MockBean
    private RestTemplate restTemplate;

    @BeforeEach
    void setUp() {
    }

    @Test
    void successRegister() {
        UserManagementServiceResponse findByMobileNumberOrEmailResponse = UserManagementServiceResponse.builder()
                .responseCode(ResponseCode.SUCCESS)
                .responseMessage(ResponseMessage.SUCCESS)
                .build();
        UserServiceResponse saveUserResponse = UserServiceResponse.builder()
                .responseCode(ResponseCode.SUCCESS)
                .responseMessage(ResponseMessage.SUCCESS)
                .build();

        when(restTemplate.postForEntity (Mockito.eq("http://localhost:9081/findUserByMobileNumberAndEmail"),
                Mockito.any(UserServiceRequest.class),
                Mockito.eq(UserManagementServiceResponse.class)))
                .thenReturn(new ResponseEntity<UserManagementServiceResponse>(findByMobileNumberOrEmailResponse, HttpStatus.OK));

        when(restTemplate.postForEntity (Mockito.eq("http://localhost:9081/saveUser"),
                Mockito.any(UserServiceRequest.class),
                Mockito.eq(UserServiceResponse.class)))
                .thenReturn(new ResponseEntity<UserServiceResponse>(saveUserResponse, HttpStatus.OK));

        UserServiceResponse userServiceResponse = userService.register(new UserServiceRequest());
        assertEquals(saveUserResponse, userServiceResponse);
    }
}