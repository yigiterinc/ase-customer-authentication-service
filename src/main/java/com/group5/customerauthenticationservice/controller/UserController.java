package com.group5.customerauthenticationservice.controller;

import com.group5.customerauthenticationservice.dto.CreateUserDto;
import com.group5.customerauthenticationservice.model.ASEDeliveryUser;
import com.group5.customerauthenticationservice.model.Role;
import com.group5.customerauthenticationservice.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ASEDeliveryUser create(@RequestBody CreateUserDto createUserDto) {
        return userService.create(createUserDto);
    }

    @GetMapping
    public ASEDeliveryUser getUser(Authentication authentication) {
        JwtAuthenticationToken token = (JwtAuthenticationToken) authentication;
        Map<String, Object> attributes = token.getTokenAttributes();
        var email = (String) attributes.get("username"); // email is encoded into username field in attributes
        return userService.findUser(email);
    }

    @PostMapping("/role")
    public Role getUserRoleFromToken(@RequestBody Authentication token) {
        JwtAuthenticationToken tokenObj = (JwtAuthenticationToken) token;
        Map<String, Object> attributes = tokenObj.getTokenAttributes();
        var email = (String) attributes.get("username"); // email is encoded into username field in attributes
        return userService.findUser(email).getRole();
    }

    @GetMapping
    public List<ASEDeliveryUser> getAllUsers() {
        return userService.getAll();
    }

    @GetMapping("/{userId}")
    public ASEDeliveryUser getUserById(@PathVariable String userId) {
        return userService.findUserById(userId);
    }

    @GetMapping("/{userId}/role")
    public Role getUserRole(@PathVariable String userId) {
        return userService.findUserById(userId).getRole();
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable String userId) {
        userService.deleteById(userId);
    }
}
