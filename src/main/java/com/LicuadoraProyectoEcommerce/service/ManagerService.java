package com.LicuadoraProyectoEcommerce.service;

import com.LicuadoraProyectoEcommerce.message.RefreshTokenForm;
import com.LicuadoraProyectoEcommerce.message.UserLoginResponse;
import com.LicuadoraProyectoEcommerce.model.Manager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public interface ManagerService {
    List<Manager> findAll();
    Manager createManager(Manager manager);

//    login
    Authentication authenticationFilter(String email, String password) throws AuthenticationException;
    UserLoginResponse userLogin(String email, String password, HttpServletRequest request);
    void refreshToken(RefreshTokenForm form, HttpServletRequest request, HttpServletResponse response) throws IOException;
}
