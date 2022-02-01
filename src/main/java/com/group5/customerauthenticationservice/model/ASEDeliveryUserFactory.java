package com.group5.customerauthenticationservice.model;

import com.group5.customerauthenticationservice.dto.CreateUserDto;

public class ASEDeliveryUserFactory {
    public static ASEDeliveryUser getUser(final CreateUserDto createUserDto) {
        var role = createUserDto.getRole();
        return getUser(role);
    }

    public static ASEDeliveryUser getUser(final Role role) {
        var roleText = role.toString();
        if (roleText.equals("DISPATCHER")) {
            return new Dispatcher();
        } else if (roleText.equals("DELIVERER") || roleText.equals("CUSTOMER")) {
            return new NormalUser();
        } else {
            throw new RuntimeException();
        }
    }
}
