package com.mitrais.test.userservice.controller;

import com.mitrais.test.userservice.dto.UserServiceRequest;
import com.mitrais.test.userservice.dto.UserServiceResponse;
import com.mitrais.test.userservice.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/")
public class UserServiceController {
    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @ResponseBody
    @PostMapping(value = "/register")
    public UserServiceResponse register(@RequestBody UserServiceRequest userServiceRequest){
        System.out.println(userServiceRequest);

        UserServiceResponse response = userService.register(userServiceRequest);

        return response;
    }
}
