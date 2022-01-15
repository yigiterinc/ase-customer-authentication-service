package com.group5.customerauthenticationservice.dto;

import com.group5.customerauthenticationservice.model.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private String email;
    private String password;
    private Role role;
}