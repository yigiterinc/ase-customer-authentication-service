package com.group5.customerauthenticationservice.model;

import javax.validation.constraints.Email;

public class Dispatcher extends ASEDeliveryUser {

    public Dispatcher() {
        super(Role.DISPATCHER);
    }

    public Dispatcher(String id, @Email String email, String password) {
        super(id, email, password, Role.DISPATCHER);
    }
}
