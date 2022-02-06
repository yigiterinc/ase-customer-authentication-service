package com.group5.customerauthenticationservice.model;

import com.group5.customerauthenticationservice.dto.CreateUserDto;

public class ASEDeliveryUserFactory {
    public static ASEDeliveryUser getUser(final CreateUserDto createUserDto) {
        var role = createUserDto.getRole();
        return getUser(role);
    }

    public static ASEDeliveryUser getUser(final Role role) {
        var roleText = role.toString();
        switch (roleText) {
            case "DISPATCHER":
                return new Dispatcher();
            case "DELIVERER":
                return new NormalUser(Role.DELIVERER);
            case "CUSTOMER":
                return new NormalUser(Role.CUSTOMER);
        }
        throw new RuntimeException();
    }
}
