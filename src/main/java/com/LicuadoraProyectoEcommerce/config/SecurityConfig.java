package com.LicuadoraProyectoEcommerce.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.cors();
//        http.authorizeRequests().antMatchers("/users/{id}", "/users", "/turns{page}", "/reserves", "/reserves/**", "/reserves{idTurn}").hasAnyAuthority("ROLE_CLIENT", "ROLE_COMPANY", "ROLE_ADMIN");
//        http.authorizeRequests().antMatchers("/turns", "/turns/{id}", "/users{page}").hasAnyAuthority("ROLE_COMPANY", "ROLE_ADMIN");
//        http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/companies/{id}").hasAnyAuthority("ROLE_COMPANY", "ROLE_ADMIN");
//        http.authorizeRequests().antMatchers(HttpMethod.PUT, "/companies/{id}").hasAnyAuthority("ROLE_COMPANY", "ROLE_ADMIN");
//        http.authorizeRequests().antMatchers(HttpMethod.POST, "/companies").hasAnyAuthority("ROLE_COMPANY", "ROLE_ADMIN");
//        http.authorizeRequests().antMatchers("/users/role/{id}").hasAnyAuthority("ROLE_ADMIN");

        http.authorizeRequests().antMatchers("/auth/**").permitAll();
//                anyRequest().authenticated();
        http.headers().frameOptions().sameOrigin();
        http.sessionManagement().sessionCreationPolicy(STATELESS);
        http.addFilterBefore(new ConfigAutorizationFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}