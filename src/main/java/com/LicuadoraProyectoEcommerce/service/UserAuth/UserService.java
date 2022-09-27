package com.LicuadoraProyectoEcommerce.service.UserAuth;

import com.LicuadoraProyectoEcommerce.dto.userAuth.UserCreateDto;
import com.LicuadoraProyectoEcommerce.dto.userAuth.UserDto;
import com.LicuadoraProyectoEcommerce.dto.userAuth.UserDtoComplete;

import java.util.List;
import java.util.Map;

public interface UserService {
   UserDto createEntity(UserCreateDto userDto);
   UserDtoComplete getById(Long id);
   List<UserDto> getUserListPagination(Integer page);
   Map<String, String> deleteById(Long id);
   UserDto updateEntity(Long idUserEntity, UserCreateDto userDto);

}
