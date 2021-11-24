package services;

import com.group5.customerauthenticationservice.service.UserService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertThrows;
import static org.testng.AssertJUnit.assertEquals;

public class UserServiceTest {

    private PasswordEncoder passwordEncoder;

    private UserService userService;

    private User testUser;

    @BeforeMethod
    private void setup() {
        this.passwordEncoder = new BCryptPasswordEncoder();
        testUser = (User) User.withUsername("user").authorities("USER")
                .passwordEncoder(passwordEncoder::encode)
                .password("test123")
                .build();
        userService = new UserService(passwordEncoder);
    }

    @Test
    public void shouldReturnUserByUsername() {
        assertEquals(userService.loadUserByUsername("user"), testUser);
    }

    @Test
    public void shouldThrowUserNotFoundException() {
        assertThrows(UsernameNotFoundException.class, () ->
                userService.loadUserByUsername("yigit123"));
    }
}
