package com.mitrais.test.usermanagementservice.repository;

import com.mitrais.test.usermanagementservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserManagementRepository extends JpaRepository<User, Long> {
    List<User> findByMobileNumberOrEmail(String mobileNumber, String email);
}
