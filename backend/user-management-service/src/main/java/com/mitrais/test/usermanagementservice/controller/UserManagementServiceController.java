package com.mitrais.test.usermanagementservice.controller;

import com.mitrais.test.usermanagementservice.constant.ResponseCode;
import com.mitrais.test.usermanagementservice.constant.ResponseMessage;
import com.mitrais.test.usermanagementservice.dto.UserDTO;
import com.mitrais.test.usermanagementservice.dto.UserManagementServiceRequest;
import com.mitrais.test.usermanagementservice.dto.UserManagementServiceResponse;
import com.mitrais.test.usermanagementservice.model.User;
import com.mitrais.test.usermanagementservice.service.UserManagementService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/")
public class UserManagementServiceController {
    @Autowired
    private UserManagementService userManagementService;

    @Autowired
    private ModelMapper modelMapper;

    @ResponseBody
    @PostMapping(value = "/saveUser")
    public UserManagementServiceResponse saveUser(@RequestBody UserManagementServiceRequest userManagementServiceRequest){
        System.out.println("saveUser"+userManagementServiceRequest);
        User user = modelMapper.map(userManagementServiceRequest, User.class);
        userManagementService.saveUSer(user);

        UserManagementServiceResponse response = UserManagementServiceResponse.builder()
                .responseCode(ResponseCode.SUCCESS)
                .responseMessage(ResponseMessage.SUCCESS)
                .build();
        return response;
    }

    @ResponseBody
    @PostMapping(value = "/findUserByMobileNumberAndEmail")
    public UserManagementServiceResponse findUserByMobileNumberAndEmail(@RequestBody UserManagementServiceRequest userManagementServiceRequest){
        System.out.println("findUserByMobileNumberAndEmail"+userManagementServiceRequest);
        User user = modelMapper.map(userManagementServiceRequest, User.class);
        List<User> findUserResponse = userManagementService.findByMobileNumberOrEmail(userManagementServiceRequest.getMobileNumber(), userManagementServiceRequest.getEmail());

        List<UserDTO> userDTOS = findUserResponse
                .stream()
                .map(userResponse -> modelMapper.map(userResponse, UserDTO.class))
                .collect(Collectors.toList());

        UserManagementServiceResponse response = UserManagementServiceResponse.builder()
                .responseCode(ResponseCode.SUCCESS)
                .responseMessage(ResponseMessage.SUCCESS)
                .users(userDTOS)
                .build();
        return response;
    }
}
