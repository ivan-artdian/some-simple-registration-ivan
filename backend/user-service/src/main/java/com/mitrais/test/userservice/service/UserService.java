package com.mitrais.test.userservice.service;

import com.mitrais.test.userservice.constant.ResponseCode;
import com.mitrais.test.userservice.constant.ResponseMessage;
import com.mitrais.test.userservice.dto.UserDTO;
import com.mitrais.test.userservice.dto.UserManagementServiceResponse;
import com.mitrais.test.userservice.dto.UserServiceRequest;
import com.mitrais.test.userservice.dto.UserServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class UserService {

    @Autowired
    RestTemplate restTemplate;
    @Value("${url.usermanagementservice}")
    String userManagementServicePath;

    public UserServiceResponse register(UserServiceRequest userServiceRequest){
        String mobileNumberAlreadyExist = "";
        String emailAlreadyExist = "";

        ResponseEntity<UserManagementServiceResponse> findUserResponse = restTemplate.postForEntity(userManagementServicePath + "/findUserByMobileNumberAndEmail", userServiceRequest, UserManagementServiceResponse.class);
        List<UserDTO> userDTOS = findUserResponse.getBody().getUsers();

        if (userDTOS != null && !userDTOS.isEmpty()){
            for (UserDTO userDTO : userDTOS){
                if(userServiceRequest.getMobileNumber().equals(userDTO.getMobileNumber())){
                    mobileNumberAlreadyExist = "Mobile Number Already Exist";
                }
                if(userServiceRequest.getEmail().equals(userDTO.getEmail())){
                    emailAlreadyExist = "Email Already Exist";
                }
            }

            UserServiceResponse response = UserServiceResponse.builder()
                    .responseCode(ResponseCode.GENERAL_FAILED)
                    .responseMessage(mobileNumberAlreadyExist + (mobileNumberAlreadyExist.equals("") ? "" : ", ") + emailAlreadyExist)
                    .build();

            return response;
        }

        ResponseEntity<UserServiceResponse> userRegistrationResponse = restTemplate.postForEntity(userManagementServicePath + "/saveUser", userServiceRequest, UserServiceResponse.class);

        return userRegistrationResponse.getBody();
    }
}
