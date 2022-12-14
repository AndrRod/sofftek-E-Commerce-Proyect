package com.LicuadoraProyectoEcommerce.serviceImpl.userAuth;
import com.LicuadoraProyectoEcommerce.config.MessageHandler;
import com.LicuadoraProyectoEcommerce.config.security.SecurityConfig;
import com.LicuadoraProyectoEcommerce.form.UserRegisterForm;
import com.LicuadoraProyectoEcommerce.dto.userAuth.UserDto;
import com.LicuadoraProyectoEcommerce.mapper.userAuth.UserMapper;
import com.LicuadoraProyectoEcommerce.exception.BadRequestException;
import com.LicuadoraProyectoEcommerce.exception.NotFoundException;
import com.LicuadoraProyectoEcommerce.form.RefreshTokenForm;
import com.LicuadoraProyectoEcommerce.message.MessageInfo;
import com.LicuadoraProyectoEcommerce.message.UserLoginResponse;
import com.LicuadoraProyectoEcommerce.message.UserRefreshTokenResponse;
import com.LicuadoraProyectoEcommerce.model.manager.BaseProduct;
import com.LicuadoraProyectoEcommerce.model.manager.Manager;
import com.LicuadoraProyectoEcommerce.model.seller.Seller;
import com.LicuadoraProyectoEcommerce.model.seller.SellerProduct;
import com.LicuadoraProyectoEcommerce.model.userAuth.Role;
import com.LicuadoraProyectoEcommerce.model.userAuth.User;
import com.LicuadoraProyectoEcommerce.repository.manager.BaseProductRepository;
import com.LicuadoraProyectoEcommerce.repository.manager.ManagerRepository;
import com.LicuadoraProyectoEcommerce.repository.seller.SellerProductRepository;
import com.LicuadoraProyectoEcommerce.repository.seller.SellerRepository;
import com.LicuadoraProyectoEcommerce.repository.UserAuth.UserRepository;
import com.LicuadoraProyectoEcommerce.service.UserAuth.UserAuthService;
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
    @Autowired
    private SellerProductRepository sellerProductRepository;
    @Autowired
    private BaseProductRepository baseProductRepository;


    public User findUserEntityById(Long id) {
        return userRepository.findById(id).orElseThrow(()-> new NotFoundException(messageHandler.message("not.found", String.valueOf(id))));
    }

    @Override
    public UserDto registerUser(UserRegisterForm userDto) {
        if(userRepository.existsByEmail(userDto.getEmail())) throw new BadRequestException("the email " + userDto.getEmail() + " already are registered");
        User user= userRepository.save(userMapper.getEntityCreateFromDto(userDto));
        return userMapper.getDtoFromEntity(user);
    }
    @Override
    public UserDto updateUser(Long id, UserRegisterForm userDto) {
        if(userRepository.existsByEmail(userDto.getEmail())) throw new BadRequestException("the email " + userDto.getEmail() + " already are registered");
        User user = findUserEntityById(id);
        User userSaved = userRepository.save(userMapper.getEntityUpdateFromDto(user, userDto));
        return userMapper.getDtoFromEntity(userSaved);
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
        if(form.getRefresh_token() == null) throw new BadRequestException(messageHandler.message("token.error", null));
        try {
            String refresh_token = form.getRefresh_token();
            Algorithm algorithm = securityConfig.algorithmFromSecretWord();
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT decodedJWT = verifier.verify(refresh_token);
            String email = decodedJWT.getSubject();
            User user = findUserByEmail(email);
            String access_token = JWT.create()
                    .withSubject(user.getEmail())
                    .withExpiresAt(new Date(System.currentTimeMillis() + 20 * 60 * 1000))
                    .withIssuer(request.getRequestURL().toString())
                    .withClaim("role", Optional.ofNullable(user.getRole().getAuthority()).stream().collect(Collectors.toList()))
                    .sign(algorithm);
            response.setContentType(APPLICATION_JSON_VALUE);
            new ObjectMapper().writeValue(response.getOutputStream(),  new UserRefreshTokenResponse("the user " + user.getEmail()+ " refresh the token successfully", access_token, refresh_token));
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
    public Seller findSellerLogged(HttpServletRequest request) {
        User sellerUser = findUserLogedByEmail(request);
        Seller seller = sellerRepository.findByUser(sellerUser).orElseThrow(()-> new BadRequestException("You don't have permissions because you are not have a seller role"));
        return seller;
    }

    @Override
    public Manager findManagerLogged(HttpServletRequest request) {
        User managerUser = findUserLogedByEmail(request);
        Manager manager = managerRepository.findByUser(managerUser).orElseThrow(()-> new BadRequestException("You don't have permissions because you are not have a manager role"));
        return manager;
    }


    @Override
    public void isSellerProductSellerCreator(HttpServletRequest request, SellerProduct sellerProduct){
        User  sellerUser = findUserLogedByEmail(request);
        Seller seller = sellerRepository.findByUser(sellerUser).orElseThrow(()-> new NotFoundException(messageHandler.message("not.authorizate", "seller")));
        if(!sellerUser.getRole().equals(Role.ADMIN) && !sellerProduct.getSeller().equals(seller))
                throw new BadRequestException(messageHandler.message("not.creator", "seller"));
    }
    @Override
    public void isTheSameUserLogged(User user, HttpServletRequest request){
        User userLogged = findUserLogedByEmail(request);
        if(user!= userLogged || userLogged.getRole().equals("ROLE_ADMIN")) throw new BadRequestException("" +
                "This user does not have permission to do this action");
    }
    @Override
    public boolean isTheSameUser(User user, HttpServletRequest request){
        User userLogged = findUserLogedByEmail(request);
        if(user!= userLogged || userLogged.getRole().equals("ROLE_ADMIN")) return false;
        return true;
    }
    @Override
    public void isManagerProductCreator(HttpServletRequest request, BaseProduct baseProduct){
        User  managerUser = findUserLogedByEmail(request);
        Manager manager = managerRepository.findByUser(managerUser).orElseThrow(()-> new NotFoundException(messageHandler.message("not.authorizate", "manager")));
        if(!managerUser.getRole().equals(Role.ADMIN) && !baseProduct.getManagerCreator().equals(manager)) throw new BadRequestException(messageHandler.message("not.creator", "manager"));
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
        Manager manager = managerRepository.findByUser(user).orElse(null);
        Seller seller = sellerRepository.findByUser(user).orElse(null);
        if(manager != null) managerRepository.delete(manager);
        if(seller != null) sellerRepository.delete(seller);
        if(user.getRole()== Role.MANAGER) managerRepository.save(new Manager(user));
        if(user.getRole()== Role.SELLER) sellerRepository.save(new Seller(user));
        return new MessageInfo(messageHandler.message("update.success", "to role: " + roleName), 200, request.getRequestURL().toString());
    }

    @Override
    public Map<String, String> deleteManagerOrSellerByIdUser(Long id) {
        User user = findUserEntityById(id);
        if(user.getRole()== null)  throw new BadRequestException(messageHandler.message("havent.role", null));
        user.setRole(Role.NONE);
        userRepository.save(user);
        Manager manager = managerRepository.findByUser(user).orElse(null);
        Seller seller = sellerRepository.findByUser(user).orElse(null);
        if(manager != null) managerRepository.delete(manager);
        if(seller != null) sellerRepository.delete(seller);
        return Map.of("Message", messageHandler.message("delete.success.entityasociate", String.valueOf(id)));
    }
}
