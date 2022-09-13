package com.LicuadoraProyectoEcommerce.service.serviceImpl;

import com.LicuadoraProyectoEcommerce.config.MessageHandler;
import com.LicuadoraProyectoEcommerce.repository.SellerRepository;
import com.LicuadoraProyectoEcommerce.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class SellerServiceImpl implements SellerService{
    @Autowired
    private SellerRepository sellerRepository;
    @Autowired
    private MessageHandler messageHandler;
    @Autowired
    private PasswordEncoder passwordEncoder;

}
