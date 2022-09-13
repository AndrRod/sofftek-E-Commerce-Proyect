package com.LicuadoraProyectoEcommerce.service.serviceImpl;

import com.LicuadoraProyectoEcommerce.config.MessageHandler;
import com.LicuadoraProyectoEcommerce.exception.NotFoundException;
import com.LicuadoraProyectoEcommerce.model.BaseProduct;
import com.LicuadoraProyectoEcommerce.model.Manager;
import com.LicuadoraProyectoEcommerce.model.Role;
import com.LicuadoraProyectoEcommerce.model.Seller;
import com.LicuadoraProyectoEcommerce.repository.ManagerRepository;
import com.LicuadoraProyectoEcommerce.repository.SellerRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

import java.util.Optional;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

//TODO
@Service
public class UserLoged {
    @Autowired
    private MessageHandler messageHandler;
    @Autowired
    private ManagerRepository managerRepository;
    @Autowired
    private SellerRepository sellerRepository;
    public String emailUserLoged(HttpServletRequest request) {
        String autorizacionHeader = request.getHeader(AUTHORIZATION);
        if(autorizacionHeader==null || !autorizacionHeader.startsWith("Bearer ")) throw new NotFoundException(messageHandler.message("not.login", null));
        String token = autorizacionHeader.substring("Bearer ".length());
        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(token);
        return decodedJWT.getSubject();
    }
    public Seller findSellerByEmail(String email) {
        return Optional.ofNullable(sellerRepository.findByEmail(email)).orElseThrow(() -> new NotFoundException(messageHandler.message("not.found", email)));
    }

    public Seller findSellerLogedByEmail(HttpServletRequest request) {
        String email = emailUserLoged(request);
        return findSellerByEmail(email);
    }

    public Manager findManagerByEmail(String email) {
        return Optional.ofNullable(managerRepository.findByEmail(email)).orElseThrow(() -> new NotFoundException(messageHandler.message("not.found", email)));
    }

    public Manager findManagerLogedByEmail(HttpServletRequest request) {
        String email = emailUserLoged(request);
        return findManagerByEmail(email);
    }
    public void isManagerAuthorizateByBaseProductCreated(Manager manager, HttpServletRequest request, BaseProduct baseProduct){
        Manager managerHeader = findManagerLogedByEmail(request);
        if(!managerHeader.equals(manager) && !baseProduct.getManager().equals(manager) && !manager.getRole().equals(Role.MANAGER))throw new NotFoundException(messageHandler.message("not.authorizate", null));
    }
    public void isUserAuthorizateCreatorAndRolSeller(Seller seller, HttpServletRequest request){
        Seller u = findSellerLogedByEmail(request);
        if(!seller.equals(u) && !u.getRole().equals(Role.SELLER))throw new NotFoundException(messageHandler.message("not.authorizate", null));
    }
}
