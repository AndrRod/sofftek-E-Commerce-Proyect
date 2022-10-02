package com.LicuadoraProyectoEcommerce.service.UserAuth;

import com.LicuadoraProyectoEcommerce.form.UserRegisterForm;
import com.LicuadoraProyectoEcommerce.dto.userAuth.UserDto;
import com.LicuadoraProyectoEcommerce.dto.userAuth.UserDtoComplete;
import com.LicuadoraProyectoEcommerce.model.userAuth.User;

import java.util.List;
import java.util.Map;

public interface UserService {
   UserDto createEntity(UserRegisterForm userDto);
   UserDtoComplete getById(Long id);
   User findEntityById(Long id);
   List<UserDto> getUserListPagination(Integer page);
   Map<String, String> deleteById(Long id);
   UserDto updateEntity(Long idUserEntity, UserRegisterForm userDto);

}
