package com.LicuadoraProyectoEcommerce.config.security;
import com.LicuadoraProyectoEcommerce.config.MessageHandler;
import com.LicuadoraProyectoEcommerce.message.MessageInfo;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@PropertySource("classpath:message_en.properties")
public class SecurityConfig {
    @Autowired
    private MessageHandler messageHandler;
    @Value("${secret.word}")
    private String secret;

    public Algorithm algorithmFromSecretWord(){
        return  Algorithm.HMAC256(secret.getBytes());
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.cors();
        http.authorizeRequests().antMatchers("/swagger-ui/**", "/api/docs/**").permitAll();
        http.authorizeRequests().antMatchers("/auth/**", "/seller/store/**", "/cart/**", "/purchase/**").permitAll();
        http.authorizeRequests().antMatchers("/seller/**").hasAnyAuthority("ROLE_SELLER", "ROLE_ADMIN");
        http.authorizeRequests().antMatchers("/manager/**").hasAnyAuthority("ROLE_MANAGER", "ROLE_ADMIN");
        http.authorizeRequests().antMatchers("/admin/**").hasAnyAuthority("ROLE_ADMIN").
                anyRequest().authenticated().and()
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler());
        http.headers().frameOptions().sameOrigin();
        http.sessionManagement().sessionCreationPolicy(STATELESS);
        http.addFilterBefore(new ConfigAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
    @Bean
    public AccessDeniedHandler accessDeniedHandler(){
        return new CustomAccessDeniedHandler();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}