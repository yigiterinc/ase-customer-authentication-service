package com.group5.customerauthenticationservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.group5.customerauthenticationservice.dto.CreateUserDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
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

    public ASEDeliveryUser(final CreateUserDto createUserDto) {
        this.email = createUserDto.getEmail();
        this.role = createUserDto.getRole();
    }

    @Id
    private String id;

    @Email
    @Indexed(unique = true)
    private String email;

    @JsonIgnore
    private String password;

    private Role role;
}
