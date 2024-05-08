package com.example.Banking_Application_Developement.config;


import com.example.Banking_Application_Developement.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private BankApplicationFilter bankApplicationFilter;

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws  Exception{
        httpSecurity.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/api/v1/register", "/api/v1/login").permitAll()
                        .requestMatchers("/api/v1/**").hasAnyAuthority(Role.ADMIN.name())
//                        .requestMatchers("/users/all").hasAnyAuthority(Role.ADMIN.name())
//                        .requestMatchers("/users/**").hasAnyAuthority(Role.USER.name())
//                        .requestMatchers("/users/all").hasAnyAuthority(Role.USER.name())
                        .anyRequest().authenticated())
                .sessionManagement( s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(bankApplicationFilter, UsernamePasswordAuthenticationFilter.class)
                .httpBasic(Customizer.withDefaults());
        return httpSecurity.build();
    }
}
