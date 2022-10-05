package com.LicuadoraProyectoEcommerce.service.UserAuth;

import com.LicuadoraProyectoEcommerce.form.UserRegisterForm;
import com.LicuadoraProyectoEcommerce.dto.userAuth.UserDto;
import com.LicuadoraProyectoEcommerce.form.RefreshTokenForm;
import com.LicuadoraProyectoEcommerce.message.MessageInfo;
import com.LicuadoraProyectoEcommerce.message.UserLoginResponse;
import com.LicuadoraProyectoEcommerce.model.manager.BaseProduct;
import com.LicuadoraProyectoEcommerce.model.userAuth.User;
import com.LicuadoraProyectoEcommerce.model.manager.Manager;
import com.LicuadoraProyectoEcommerce.model.seller.Seller;
import com.LicuadoraProyectoEcommerce.model.seller.SellerProduct;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public interface UserAuthService {
    UserDto registerUser(UserRegisterForm userDto);
    UserDto updateUser(Long id, UserRegisterForm userDto);
    UserLoginResponse userLogin(String email, String password, HttpServletRequest request);
    void refreshToken(RefreshTokenForm form, HttpServletRequest request, HttpServletResponse response) throws IOException;
    User findUserLogedByEmail(HttpServletRequest request);
    String emailUserLoged(HttpServletRequest request);
    MessageInfo updateUserRol(Long idUser, String roleName, HttpServletRequest request);
    User getUserLoged(HttpServletRequest request);
    User findUserByEmail(String email);

    Map<String, String> deleteManagerOrSellerByIdUser(Long id);
    void isManagerProductCreator(HttpServletRequest request, BaseProduct baseProduct);
    void isSellerProductSellerCreator(HttpServletRequest request, SellerProduct sellerProduct);

    Seller findSellerLogged(HttpServletRequest request);

    Manager findManagerLogged(HttpServletRequest request);

    void isTheSameUserLogged(User user, HttpServletRequest request);
    boolean isTheSameUser(User user, HttpServletRequest request);
}
