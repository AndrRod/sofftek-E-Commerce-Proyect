package com.LicuadoraProyectoEcommerce.service;

import com.LicuadoraProyectoEcommerce.message.RefreshTokenForm;
import com.LicuadoraProyectoEcommerce.message.UserLoginResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface UserLoginRegister {
    Authentication authenticationFilter(String email, String password) throws AuthenticationException;
    UserLoginResponse userLogin(String email, String password, HttpServletRequest request);
    void refreshToken(RefreshTokenForm form, HttpServletRequest request, HttpServletResponse response) throws IOException;
}
