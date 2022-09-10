package com.LicuadoraProyectoEcommerce.controller;

import com.LicuadoraProyectoEcommerce.form.UserLoginForm;
import com.LicuadoraProyectoEcommerce.message.RefreshTokenForm;
import com.LicuadoraProyectoEcommerce.message.UserLoginResponse;
import com.LicuadoraProyectoEcommerce.model.Manager;
import com.LicuadoraProyectoEcommerce.model.Seller;
import com.LicuadoraProyectoEcommerce.service.ManagerService;
import com.LicuadoraProyectoEcommerce.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/auth")
public class UserAuthController {
    @Autowired
    private ManagerService managerService;
    @Autowired
    private SellerService sellerService;
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/manager/login")
    public UserLoginResponse loginUser(@RequestBody @Valid UserLoginForm userLogin, HttpServletRequest request){
        return managerService.userLogin(userLogin.getEmail(), userLogin.getPassword(), request);
    }
    @PostMapping("/manager/refresh")
    @ResponseStatus(HttpStatus.OK)
    public void refreshToken(@RequestBody RefreshTokenForm form, HttpServletRequest request, HttpServletResponse response) throws IOException, IOException {
        managerService.refreshToken(form, request, response);
    }

    @PostMapping("/manager/register")
    private ResponseEntity<Manager> createManager(@RequestBody Manager manager){
        return ResponseEntity.status(201).body(managerService.createManager(manager));
    }
    @PostMapping("/seller/register")
    private ResponseEntity<Seller> createSeller(@RequestBody Seller seller){
        return ResponseEntity.status(201).body(sellerService.createSeller(seller));
    }
}