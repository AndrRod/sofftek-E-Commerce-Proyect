package com.LicuadoraProyectoEcommerce.service;

import com.LicuadoraProyectoEcommerce.dto.UserCreateDto;
import com.LicuadoraProyectoEcommerce.dto.UserDto;
import com.LicuadoraProyectoEcommerce.form.RefreshTokenForm;
import com.LicuadoraProyectoEcommerce.message.MessageInfo;
import com.LicuadoraProyectoEcommerce.message.UserLoginResponse;
import com.LicuadoraProyectoEcommerce.model.BaseProduct;
import com.LicuadoraProyectoEcommerce.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface UserAuthService {
    UserDto registerUser(UserCreateDto userDto);
    UserLoginResponse userLogin(String email, String password, HttpServletRequest request);
    void refreshToken(RefreshTokenForm form, HttpServletRequest request, HttpServletResponse response) throws IOException;
    User findUserLogedByEmail(HttpServletRequest request);
    String emailUserLoged(HttpServletRequest request);
    MessageInfo updateUserRol(Long idUser, String roleName, HttpServletRequest request);
    void isTheUserCreatorOfProduct(User user, HttpServletRequest request, BaseProduct product);
    User getUserLoged(HttpServletRequest request);
}
