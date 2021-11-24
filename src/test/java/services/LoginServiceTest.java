package services;

import com.group5.customerauthenticationservice.IncorrectCredentialsException;
import com.group5.customerauthenticationservice.config.JwtCreator;
import com.group5.customerauthenticationservice.service.LoginService;
import com.group5.customerauthenticationservice.service.UserService;
import org.mockito.Mock;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.mockito.AdditionalMatchers.not;
import static org.mockito.ArgumentMatchers.eq;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertThrows;

public class LoginServiceTest {

    @Mock
    private JwtCreator jwtCreator;

    @Mock
    private UserService userService;

    PasswordEncoder passwordEncoder;

    private LoginService loginService;

    private final String sampleToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwibmJmIjoxNjM3NzEyMjEyLCJleHAiOjE2Mzc3OTg2MTIsInVzZXJJZCI6IjEiLCJhdXRob3JpdGllcyI6IlVTRVIiLCJ1c2VybmFtZSI6InVzZXIifQ.Ue68ED-3zrxj7w2VDL_xwoSMAhdDwJLinMvcX5qbXP7g5Y5EbQwxOD97MM7evZyC3BsOihcaXtQelhFe1ay2CucI5qKdLmK4qjxE0vSIgejQ683HiBRJoP3zlquzhXD444fqTD_dQDu-sxYsFw27LVyn0Uq1MTom1HArzoXgiXIN1xwUN6HR5U_uObCnulUt6vTfxFmUjBuQPEMmDPPG2Fv92vc0wfkldojpGcxudokNWGEpywOv3rMoS_hfHTvuL6rdqpIL7-d13lE8RzJb_zQv_9qCNzTs2OZSRthKKeQs5aEQaNBufBYqfnVf1ji4XHvRoJiyTbRrdVj6abdwig";

    @BeforeTest
    public void setup() {
        this.passwordEncoder = new BCryptPasswordEncoder();
        initMocks(this);
        this.loginService = new LoginService(jwtCreator, userService, passwordEncoder);
        var user = (User) User.withUsername("user").authorities("USER")
                .passwordEncoder(passwordEncoder::encode)
                .password("test123")
                .build();

        when(userService.loadUserByUsername(anyString())).thenReturn(user);
        when(jwtCreator.createJwtForClaims(any(), any())).thenReturn(sampleToken);
        when(userService.loadUserByUsername(not(eq("user")))).thenThrow(UsernameNotFoundException.class);
    }

    @Test
    public void whenCorrectCredentialsShouldReturnJwt() {
        assertEquals(loginService.login("user", "test123"), sampleToken);
        verify(jwtCreator, times(1)).createJwtForClaims(anyString(), anyMap());
        reset(jwtCreator);
    }

    @Test
    public void whenIncorrectCredentialsShouldThrowIncorrectCredentialsException() {
        assertThrows(IncorrectCredentialsException.class,
                    () -> loginService.login("user", "wrongpass"));
        verify(jwtCreator, times(0)).createJwtForClaims(anyString(), anyMap());
    }
}
