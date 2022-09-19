package com.LicuadoraProyectoEcommerce.serviceImpl;
import com.LicuadoraProyectoEcommerce.config.MessageHandler;
import com.LicuadoraProyectoEcommerce.config.security.SecurityConfig;
import com.LicuadoraProyectoEcommerce.dto.UserCreateDto;
import com.LicuadoraProyectoEcommerce.dto.UserDto;
import com.LicuadoraProyectoEcommerce.dto.mapper.UserMapper;
import com.LicuadoraProyectoEcommerce.exception.BadRequestException;
import com.LicuadoraProyectoEcommerce.exception.NotFoundException;
import com.LicuadoraProyectoEcommerce.form.RefreshTokenForm;
import com.LicuadoraProyectoEcommerce.message.MessageInfo;
import com.LicuadoraProyectoEcommerce.message.UserLoginResponse;
import com.LicuadoraProyectoEcommerce.model.*;
import com.LicuadoraProyectoEcommerce.model.manager.BaseProduct;
import com.LicuadoraProyectoEcommerce.model.manager.Manager;
import com.LicuadoraProyectoEcommerce.model.seller.Seller;
import com.LicuadoraProyectoEcommerce.repository.manager.ManagerRepository;
import com.LicuadoraProyectoEcommerce.repository.seller.SellerRepository;
import com.LicuadoraProyectoEcommerce.repository.UserRepository;
import com.LicuadoraProyectoEcommerce.service.UserAuthService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.vavr.control.Try;
import org.springframework.beans.factory.annotation.Autowired;
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

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@Service
public class UserAuthServiceImpl implements UserAuthService, UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MessageHandler messageHandler;

    @Autowired
    private ManagerRepository managerRepository;
    @Autowired
    private SellerRepository sellerRepository;
    @Autowired
    private SecurityConfig securityConfig;
    public User findUserEntityById(Long id) {
        return userRepository.findById(id).orElseThrow(()-> new NotFoundException(messageHandler.message("not.found", String.valueOf(id))));
    }

    @Override
    public UserDto registerUser(UserCreateDto userDto) {
        User user= userRepository.save(userMapper.getEntityCreateFromDto(userDto));
        return userMapper.getDtoFromEntity(user);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = findUserByEmail(email);
        Collection<SimpleGrantedAuthority> authorizations = Collections.singleton(new SimpleGrantedAuthority(user.getRole().getAuthority()));
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorizations);
    }
    @Override
    public UserLoginResponse userLogin(String email, String password, HttpServletRequest request) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        User user = findUserByEmail(email);
        if(password == null) throw new BadRequestException(messageHandler.message("password.error",null));
        if(!passwordEncoder.matches(password, user.getPassword())) throw new BadRequestException(messageHandler.message("password.error",null));
        if(user.getRole()== null) throw new BadRequestException(messageHandler.message("not.have.rol",null));
        Algorithm algorithm = securityConfig.algorithmFromSecretWord();
        String access_token = JWT.create()
                .withSubject(user.getEmail())
                .withExpiresAt(new Date(System.currentTimeMillis() + 600 * 60 * 1000))
                .withIssuer(request.getRequestURL().toString())
                .withClaim("role",List.of(user.getRole().getAuthority()))
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
            Algorithm algorithm = securityConfig.algorithmFromSecretWord();
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT decodedJWT = verifier.verify(refresh_token);
            String email = decodedJWT.getSubject();
            User user = findUserByEmail(email);
            String acceso_token = JWT.create()
                    .withSubject(user.getEmail())
                    .withExpiresAt(new Date(System.currentTimeMillis() + 20 * 60 * 1000))
                    .withIssuer(request.getRequestURL().toString())
                    .withClaim("role", Optional.ofNullable(user.getRole().getAuthority()).stream().collect(Collectors.toList()))
                    .sign(algorithm);
            response.setContentType(APPLICATION_JSON_VALUE);
            new ObjectMapper().writeValue(response.getOutputStream(),  new HashMap<>(){{put("message", "the user " + user.getEmail()+ " refresh the token succesfully"); put("access_token", acceso_token); put("update_token", refresh_token);}});
        }catch (Exception exception){
            response.setStatus(FORBIDDEN.value());
            response.setContentType(APPLICATION_JSON_VALUE);
            new ObjectMapper().writeValue(response.getOutputStream(), new MessageInfo(exception.getMessage(), 403, request.getRequestURI()));
        }

    }

    @Override
    public String emailUserLoged(HttpServletRequest request) {
        String autorizacionHeader = request.getHeader(AUTHORIZATION);
        if(autorizacionHeader==null || !autorizacionHeader.startsWith("Bearer ")) throw new NotFoundException(messageHandler.message("not.login", null));
        String token = autorizacionHeader.substring("Bearer ".length());
        Algorithm algorithm = securityConfig.algorithmFromSecretWord();
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(token);
        return decodedJWT.getSubject();
    }
    @Override
    public User findUserByEmail(String email) {
        return Optional.ofNullable(userRepository.findByEmail(email)).orElseThrow(() -> new NotFoundException(messageHandler.message("not.found.email", email)));
    }



    @Override
    public User findUserLogedByEmail(HttpServletRequest request) {
        String email = emailUserLoged(request);
        return findUserByEmail(email);
    }


    @Override
    public void isTheUserCreatorOfProduct(User user, HttpServletRequest request, BaseProduct product) {
        User u = getUserLoged(request);
        if(!user.equals(u) && !u.getRole().equals(Role.MANAGER))throw new NotFoundException(messageHandler.message("not.authorizate", null));
    }

    @Override
    public User getUserLoged(HttpServletRequest request) {
        String autorizacionHeader = request.getHeader(AUTHORIZATION);
        if(autorizacionHeader==null || !autorizacionHeader.startsWith("Bearer ")) throw new NotFoundException(messageHandler.message("not.login", null));
        String token = autorizacionHeader.substring("Bearer ".length());
        Algorithm algorithm = securityConfig.algorithmFromSecretWord();
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(token);
        return findUserByEmail(decodedJWT.getSubject());
    }

    @Override
    public MessageInfo updateUserRol(Long idUser, String roleName, HttpServletRequest request) {
        User user = findUserEntityById(idUser);
        if(user.getRole()!= null) {if( roleName.equals(user.getRole().name())) throw new BadRequestException(messageHandler.message("same.role", roleName));}
        Try.of(() -> {user.setRole(Role.valueOf(roleName)); return userRepository.save(user);
        }).onFailure(e -> {throw new NotFoundException(messageHandler.message("not.found.rol", roleName));});
        Manager manager = managerRepository.findByUser(user);
        Seller seller = sellerRepository.findByUser(user);
        if(manager != null) managerRepository.delete(manager);
        if(seller != null) sellerRepository.delete(seller);
        if(user.getRole()== Role.MANAGER) managerRepository.save(new Manager(null, user, null));
        if(user.getRole()== Role.SELLER) sellerRepository.save(new Seller(null, user, null));
        return new MessageInfo(messageHandler.message("update.success", "to role: " + roleName), 200, request.getRequestURL().toString());
    }

    @Override
    public Map<String, String> deleteManagerOrSellerByIdUser(Long id) {
        User user = findUserEntityById(id);
        if(user.getRole()== null)  throw new BadRequestException(messageHandler.message("havent.role", null));
        user.setRole(Role.NONE);
        userRepository.save(user);
        Manager manager = managerRepository.findByUser(user);
        Seller seller = sellerRepository.findByUser(user);
        if(manager != null) managerRepository.delete(manager);
        if(seller != null) sellerRepository.delete(seller);
        return Map.of("Message", messageHandler.message("delete.success.entityasociate", String.valueOf(id)));
    }


}
