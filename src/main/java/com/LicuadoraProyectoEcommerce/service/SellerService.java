package com.LicuadoraProyectoEcommerce.service;

import com.LicuadoraProyectoEcommerce.dto.SellerCompleteDto;
import com.LicuadoraProyectoEcommerce.dto.SellerDto;
import com.LicuadoraProyectoEcommerce.form.RefreshTokenForm;
import com.LicuadoraProyectoEcommerce.message.UserLoginResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface SellerService {
    List<SellerDto> findAll(Integer page);
    SellerDto createSeller(SellerCompleteDto seller);
    Map<String, String> deleteSeller(Long id);
    SellerDto findById(Long id);

    //    LOGIN AND REFRESH TOKEN
    UserLoginResponse userLogin(String email, String password, HttpServletRequest request);
    void refreshToken(RefreshTokenForm form, HttpServletRequest request, HttpServletResponse response) throws IOException;

    SellerDto updateSeller(Long id, SellerCompleteDto sellerCompleteDto);
}
