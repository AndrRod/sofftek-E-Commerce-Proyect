package com.LicuadoraProyectoEcommerce.service;

import com.LicuadoraProyectoEcommerce.dto.UserDto;
import com.LicuadoraProyectoEcommerce.dto.UserDtoComplete;
import com.LicuadoraProyectoEcommerce.model.User;

import java.util.List;
import java.util.Map;

public interface UserService {
   UserDto createEntity(UserDto userDto);
   UserDtoComplete getById(Long id);
   List<UserDto> getUserListPagination(Integer page);
   Map<String, String> deleteById(Long id);
   UserDto updateEntity(Long idUserEntity, UserDto userDto);
}
