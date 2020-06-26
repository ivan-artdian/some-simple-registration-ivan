package com.mitrais.test.usermanagementservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import com.mitrais.test.usermanagementservice.dto.UserDTO;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class UserManagementServiceResponse {
    private String responseCode;
    private String responseMessage;
    private List<UserDTO> users;
}
