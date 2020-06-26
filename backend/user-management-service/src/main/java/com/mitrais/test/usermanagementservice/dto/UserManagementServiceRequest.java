package com.mitrais.test.usermanagementservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UserManagementServiceRequest {
    private String mobileNumber;
    private String firstName;
    private String lastName;
    private String monthOfBirth;
    private String dateOfBirth;
    private String yearOfBirth;
    private String gender;
    private String email;
}
