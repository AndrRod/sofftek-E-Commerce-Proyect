package com.LicuadoraProyectoEcommerce.service;

import com.LicuadoraProyectoEcommerce.model.Seller;

import java.util.List;

public interface SellerService {
    List<Seller> findAll();
    Seller createSeller(Seller seller);
}
