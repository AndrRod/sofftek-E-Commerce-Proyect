package com.LicuadoraProyectoEcommerce.service.serviceImpl;

import com.LicuadoraProyectoEcommerce.config.MessageHandler;
import com.LicuadoraProyectoEcommerce.dto.SellerCompleteDto;
import com.LicuadoraProyectoEcommerce.dto.SellerDto;
import com.LicuadoraProyectoEcommerce.dto.mapper.SellerMapper;
import com.LicuadoraProyectoEcommerce.exception.BadRequestException;
import com.LicuadoraProyectoEcommerce.exception.NotFoundException;
import com.LicuadoraProyectoEcommerce.message.MessageInfo;
import com.LicuadoraProyectoEcommerce.form.RefreshTokenForm;
import com.LicuadoraProyectoEcommerce.message.UserLoginResponse;
import com.LicuadoraProyectoEcommerce.model.Seller;
import com.LicuadoraProyectoEcommerce.repository.SellerRepository;
import com.LicuadoraProyectoEcommerce.service.SellerService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Service
public class SellerServiceImpl implements SellerService, UserDetailsService {
    @Autowired
    private SellerRepository sellerRepository;
    @Autowired
    private MessageHandler messageHandler;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private SellerMapper sellerMapper;

    private final int SIZE_TEN = 10;
    @Override
    public List<SellerDto> findAll(Integer page) {
        return sellerMapper.getListDtoFromListEntity(sellerRepository.findAll(PageRequest.of(page, SIZE_TEN)).getContent());
    }
    @Override
    public SellerDto createSeller(SellerCompleteDto seller) {
        return sellerMapper.getDtoFromEntity(sellerRepository.save(sellerMapper.getEntityCreateFromDto(seller)));
    }

    @Override
    public Map<String, String> deleteSeller(Long id) {
        sellerRepository.delete(findEntityById(id));
        return Map.of("Message", messageHandler.message("delete.success", String.valueOf(id)));
    }

    @Override
    public SellerDto findById(Long id) {
        return sellerMapper.getDtoFromEntity(findEntityById(id));
    }

    @Override
    public SellerDto updateSeller(Long id, SellerCompleteDto sellerCompleteDto) {
        return sellerMapper.getDtoFromEntity(sellerRepository.save(sellerMapper.getEntityUpdateFromDto(findEntityById(id), sellerCompleteDto)));
    }

    public Seller findEntityById(Long id) {
        return sellerRepository.findById(id).orElseThrow(()-> new NotFoundException(messageHandler.message("not.found", String.valueOf((id)))));
    }
    public Seller findUserByEmail(String email) {
        return Optional.ofNullable(sellerRepository.findByEmail(email)).orElseThrow(() -> new NotFoundException(messageHandler.message("not.found", email)));
    }


//    LOGIN AND REFRESH TOKEN
    @Override
    public UserLoginResponse userLogin(String email, String password, HttpServletRequest request) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Seller user = findUserByEmail(email);
        if(password == null) throw new BadRequestException(messageHandler.message("password.error",null));
        if(!passwordEncoder.matches(password, user.getPassword())) throw new BadRequestException(messageHandler.message("password.error",null));
        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
        String access_token = JWT.create()
                .withSubject(user.getEmail())
                .withExpiresAt(new Date(System.currentTimeMillis() + 600 * 60 * 1000))
                .withIssuer(request.getRequestURL().toString())
                .withClaim("role", List.of(user.getRole().getAuthority()))
                .sign(algorithm);
        String update_token = JWT.create()
                .withSubject(user.getEmail())
                .withExpiresAt(new Date(System.currentTimeMillis() + 30 * 60 * 1000))
                .withIssuer(request.getRequestURL().toString())
                .sign(algorithm);
        return new UserLoginResponse(user.getEmail(), user.getRole(),  access_token,  update_token);
    }

    @Override
    public void refreshToken(RefreshTokenForm form, HttpServletRequest request, HttpServletResponse response) throws IOException {
        if(form.getRefresh_token() == null || !form.getRefresh_token().startsWith("Bearer ")) throw new BadRequestException(messageHandler.message("token.error", null));
        try {
            String refresh_token = form.getRefresh_token().substring("Bearer ".length());
            Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT decodedJWT = verifier.verify(refresh_token);
            String email = decodedJWT.getSubject();
            Seller user = findUserByEmail(email);
            String access_token = JWT.create()
                    .withSubject(user.getEmail())
                    .withExpiresAt(new Date(System.currentTimeMillis() + 20 * 60 * 1000))
                    .withIssuer(request.getRequestURL().toString())
                    .withClaim("role", Optional.ofNullable(user.getRole().getAuthority()).stream().collect(Collectors.toList()))
                    .sign(algorithm);
            response.setContentType(APPLICATION_JSON_VALUE);
            new ObjectMapper().writeValue(response.getOutputStream(),  new HashMap<>(){{put("message", "the user " + user.getEmail()+ " refresh the token succesfully"); put("access_token", access_token); put("update_token", refresh_token);}});
        }catch (Exception exception){
            response.setStatus(FORBIDDEN.value());
            response.setContentType(APPLICATION_JSON_VALUE);
            new ObjectMapper().writeValue(response.getOutputStream(), new MessageInfo(exception.getMessage(), 403, request.getRequestURI()));
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Seller user = findUserByEmail(email);
        Collection<SimpleGrantedAuthority> authorizations = Collections.singleton(new SimpleGrantedAuthority(user.getRole().getAuthority()));
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorizations);
    }
}
