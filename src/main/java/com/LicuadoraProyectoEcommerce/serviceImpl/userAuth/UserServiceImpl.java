package com.LicuadoraProyectoEcommerce.serviceImpl.userAuth;
import com.LicuadoraProyectoEcommerce.config.MessageHandler;
import com.LicuadoraProyectoEcommerce.config.security.SecurityConfig;
import com.LicuadoraProyectoEcommerce.form.UserRegisterForm;
import com.LicuadoraProyectoEcommerce.dto.userAuth.UserDto;
import com.LicuadoraProyectoEcommerce.dto.userAuth.UserDtoComplete;
import com.LicuadoraProyectoEcommerce.mapper.userAuth.UserMapper;
import com.LicuadoraProyectoEcommerce.exception.NotFoundException;
import com.LicuadoraProyectoEcommerce.model.userAuth.User;
import com.LicuadoraProyectoEcommerce.repository.UserAuth.UserRepository;
import com.LicuadoraProyectoEcommerce.service.UserAuth.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    private static final int SIZE_TEN = 10;
    @Autowired
    private MessageHandler messageHandler;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private SecurityConfig securityConfig;
    @Override
    public UserDto createEntity(UserRegisterForm userDto) {
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
    public UserDto updateEntity(Long idUserEntity, UserRegisterForm userDto) {
        User user = userRepository.save(userMapper.getEntityUpdateFromDto(findEntityById(idUserEntity), userDto));
        return userMapper.getDtoFromEntity(user);
    }

    @Override
    public UserDto getUserDtoById(Long id) {
        return userMapper.getDtoFromEntity(findEntityById(id));
    }

    @Override
    public User findEntityById(Long id){
        return userRepository.findById(id).orElseThrow(()-> new NotFoundException(messageHandler.message("not.found", String.valueOf(id))));
    }
}
