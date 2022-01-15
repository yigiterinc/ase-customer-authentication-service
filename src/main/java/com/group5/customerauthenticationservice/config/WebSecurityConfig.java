package com.group5.customerauthenticationservice.config;

import com.google.common.collect.ImmutableMap;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Map;

@Component
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final ImmutableMap<String, HttpMethod> INSECURE_ENDPOINTS = ImmutableMap.of(
            "/error", HttpMethod.GET,
            "/user", HttpMethod.POST,
            "/login", HttpMethod.POST);

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests(configurer ->
                        INSECURE_ENDPOINTS.keySet().forEach(endpoint -> {
                            var requestType = INSECURE_ENDPOINTS.get(endpoint);
                            configurer.antMatchers(requestType, endpoint)
                                    .permitAll();
                        })
                ).authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
    }
}
