package com.mitrais.test.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserServiceRequest {
    private String mobileNumber;
    private String firstName;
    private String lastName;
    private String monthOfBirth;
    private String dateOfBirth;
    private String yearOfBirth;
    private String gender;
    private String email;
}
