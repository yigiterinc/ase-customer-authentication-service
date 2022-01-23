package com.group5.customerauthenticationservice.service;

import com.group5.customerauthenticationservice.dto.UserDto;
import com.group5.customerauthenticationservice.model.ASEDeliveryUser;
import com.group5.customerauthenticationservice.model.ASEDeliveryUserFactory;
import com.group5.customerauthenticationservice.repository.ASEDeliveryUserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Set;

@Service
public class UserService implements UserDetailsService {
    private final PasswordEncoder passwordEncoder;
    private final ASEDeliveryUserRepository aseDeliveryUserRepository;

    public UserService(PasswordEncoder passwordEncoder, ASEDeliveryUserRepository aseDeliveryUserRepository) {
        this.passwordEncoder = passwordEncoder;
        this.aseDeliveryUserRepository = aseDeliveryUserRepository;
    }

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        ASEDeliveryUser user = aseDeliveryUserRepository
                .findASEDeliveryUserByEmail(email)
                .orElseThrow(RuntimeException::new);

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), getAuthority(user));
    }

    public ASEDeliveryUser findUser(String email) {
        return aseDeliveryUserRepository.findASEDeliveryUserByEmail(email).orElseThrow(RuntimeException::new);
    }

    private Set<SimpleGrantedAuthority> getAuthority(ASEDeliveryUser user) {
        return Collections.singleton(new SimpleGrantedAuthority("ROLE_" + user.getRole()));
    }

    public ASEDeliveryUser create(UserDto userDto) {
        var user = ASEDeliveryUserFactory.getUser(userDto);
        user.setEmail(userDto.getEmail());
        user.setRole(userDto.getRole());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        return aseDeliveryUserRepository.save(user);
    }
}
