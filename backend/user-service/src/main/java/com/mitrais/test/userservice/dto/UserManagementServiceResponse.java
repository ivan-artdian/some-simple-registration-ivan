package com.mitrais.test.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserManagementServiceResponse {
    private String responseCode;
    private String responseMessage;
    private List<UserDTO> users;
}
