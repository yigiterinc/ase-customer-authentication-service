package com.group5.customerauthenticationservice.controller;

import com.group5.customerauthenticationservice.dto.UserDto;
import com.group5.customerauthenticationservice.model.ASEDeliveryUser;
import com.group5.customerauthenticationservice.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ASEDeliveryUser create(@RequestBody UserDto userDto) {
        return userService.create(userDto);
    }

    @GetMapping
    public ASEDeliveryUser getUser(Authentication authentication) {
        JwtAuthenticationToken token = (JwtAuthenticationToken) authentication;
        Map<String, Object> attributes = token.getTokenAttributes();
        var email = (String) attributes.get("username"); // email is encoded into username field in attributes
        return userService.findUser(email);
    }
}
