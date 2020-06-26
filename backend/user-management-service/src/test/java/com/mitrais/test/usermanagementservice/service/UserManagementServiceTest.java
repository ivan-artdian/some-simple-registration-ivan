package com.mitrais.test.usermanagementservice.service;

import com.mitrais.test.usermanagementservice.model.User;
import com.mitrais.test.usermanagementservice.repository.UserManagementRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserManagementService.class)
class UserManagementServiceTest {
    @Autowired
    private UserManagementService userManagementService;
    @MockBean
    private UserManagementRepository userManagementRepository;

    @Test
    void saveUser(){
        when(userManagementRepository.save(Mockito.any(User.class))).thenReturn(new User());
        userManagementService.saveUSer(new User());

        verify(userManagementRepository, times(1)).save(Mockito.any(User.class));
    }

    @Test
    void findByMobileNumberOrEmail() {
        List<User> users = new ArrayList<>();
        users.add(new User(1L, "a", "b", "c", "d", "e", "f", "g", "h"));
        users.add(new User(2L, "b", "c", "d", "e", "f", "g", "h", "a"));
        users.add(new User(3L, "c", "d", "e", "f", "g", "h", "a", "b"));

        when(userManagementRepository.findByMobileNumberOrEmail(Mockito.any(String.class), Mockito.any(String.class)))
                .thenReturn(users);

        List<User> usersResponse = userManagementService.findByMobileNumberOrEmail("08126548665", "asd@email.com");
        System.out.println(usersResponse);

        assertNotNull(usersResponse);
        assertEquals(1L, usersResponse.get(0).getId());
        assertEquals("b", usersResponse.get(1).getMobileNumber());
    }
}