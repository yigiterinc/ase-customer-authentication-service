package com.group5.customerauthenticationservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.group5.customerauthenticationservice.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "users")
public abstract class ASEDeliveryUser {

    public ASEDeliveryUser(Role role) {
        this.role = role;
    }

    public ASEDeliveryUser(final String email, final String password, final Role role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public ASEDeliveryUser(final UserDto userDto) {
        this.email = userDto.getEmail();
        this.role = userDto.getRole();
    }

    @Id
    private String id;

    @Email
    private String email;

    @JsonIgnore
    private String password;

    private Role role;
}
