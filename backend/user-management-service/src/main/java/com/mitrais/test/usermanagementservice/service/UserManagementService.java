package com.mitrais.test.usermanagementservice.service;

import com.mitrais.test.usermanagementservice.model.User;
import com.mitrais.test.usermanagementservice.repository.UserManagementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserManagementService {
    @Autowired
    private UserManagementRepository userManagementRepository;

    public void saveUSer(User user){
        User savedUser = userManagementRepository.save(user);
    }

    public List<User> findByMobileNumberOrEmail(String mobileNumber, String email){
        return userManagementRepository.findByMobileNumberOrEmail(mobileNumber, email);
    }
}
