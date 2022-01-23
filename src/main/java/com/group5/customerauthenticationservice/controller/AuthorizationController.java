package com.group5.customerauthenticationservice.controller;

import com.group5.customerauthenticationservice.model.Role;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthorizationController {

    @PostMapping
    public Role authorize() {
        return Role.CUSTOMER;
    }
}
