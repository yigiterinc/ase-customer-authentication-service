package com.group5.customerauthenticationservice.model;

import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;

/**
 * A normal user is a user who is either a customer or deliverer
 * in other words, someone with no managerial permissions
 */
@NoArgsConstructor
public class NormalUser extends ASEDeliveryUser {
    public NormalUser(final String id,
                      final @Email String email,
                      final String password,
                      final Role role,
                      final byte[] rfid) {

        super(id, email, role, password);
        this.rfid = rfid;
    }

    private byte[] rfid = new byte[8];

    public byte[] getRfid() {
        return rfid;
    }

    public void setRfid(byte[] rfid) {
        this.rfid = rfid;
    }

}
