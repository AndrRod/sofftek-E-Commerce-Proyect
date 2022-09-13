package com.LicuadoraProyectoEcommerce.service.serviceImpl;
import com.LicuadoraProyectoEcommerce.config.MessageHandler;
import com.LicuadoraProyectoEcommerce.dto.UserDto;
import com.LicuadoraProyectoEcommerce.dto.UserDtoComplete;
import com.LicuadoraProyectoEcommerce.dto.mapper.UserMapper;
import com.LicuadoraProyectoEcommerce.exception.NotFoundException;
import com.LicuadoraProyectoEcommerce.model.BaseProduct;
import com.LicuadoraProyectoEcommerce.model.Role;
import com.LicuadoraProyectoEcommerce.model.User;
import com.LicuadoraProyectoEcommerce.repository.UserRepository;
import com.LicuadoraProyectoEcommerce.service.UserService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Service
public class UserServiceImpl implements UserService {
    private static final int SIZE_TEN = 10;
    @Autowired
    private MessageHandler messageHandler;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;
    @Override
    public UserDto createEntity(UserDto userDto) {
        User user= userRepository.save(userMapper.getEntityCreateFromDto(userDto));
        return userMapper.getDtoFromEntity(user);
    }

    @Override
    public UserDtoComplete getById(Long id) {
        return userMapper.getDtoCompleteFromEntity(findEntityById(id));
    }

    @Override
    public List<UserDto> getUserListPagination(Integer page) {
        return userMapper.getListDtoFromListEntity(userRepository.findAll(PageRequest.of(page, SIZE_TEN)).getContent());
    }

    @Override
    public Map<String, String> deleteById(Long id) {
        userRepository.delete(findEntityById(id));
        return Map.of("Message", messageHandler.message("delete.success", String.valueOf(id)));
    }

    @Override
    public UserDto updateEntity(Long idUserEntity, UserDto userDto) {
        User user = userRepository.save(userMapper.getEntityUpdateFromDto(findEntityById(idUserEntity), userDto));
        return userMapper.getDtoFromEntity(user);
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
        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(token);
        return findUserByEmail(decodedJWT.getSubject());
    }

    public User findUserByEmail(String email) {
        return Optional.ofNullable(userRepository.findByEmail(email)).orElseThrow(() -> new NotFoundException(messageHandler.message("not.found", email)));
    }
    public User findEntityById(Long id){
        return userRepository.findById(id).orElseThrow(()-> new NotFoundException(messageHandler.message("not.found", String.valueOf(id))));
    }
}
