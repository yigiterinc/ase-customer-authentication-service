package com.group5.customerauthenticationservice.controller;

import com.group5.customerauthenticationservice.service.LoginService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {

    final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(path = "/login")
    public String login(@RequestParam String email,
                        @RequestParam String password) {

        return loginService.login(email, password);
    }
}
