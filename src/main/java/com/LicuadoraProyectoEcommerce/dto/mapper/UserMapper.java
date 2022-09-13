package com.LicuadoraProyectoEcommerce.dto.mapper;

import com.LicuadoraProyectoEcommerce.dto.UserDto;
import com.LicuadoraProyectoEcommerce.dto.UserDtoComplete;
import com.LicuadoraProyectoEcommerce.model.Role;
import com.LicuadoraProyectoEcommerce.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class UserMapper {
    @Autowired
    private PasswordEncoder passwordEncoder;
    public UserDto getDtoFromEntity(User user){return new UserDto(user.getName(), user.getEmail(), user.getPassword());}
    public UserDtoComplete getDtoCompleteFromEntity(User user){return new UserDtoComplete(user.getName(), user.getEmail(), user.getPassword(), user.getRole());}
    public List<UserDto> getListDtoFromListEntity(List<User> userList){return userList.stream().map(this::getDtoFromEntity).collect(Collectors.toList());}

    public User getEntityCreateFromDto(UserDto userDtoComplete){
        return new User(null, userDtoComplete.getName(), userDtoComplete.getEmail(), passwordEncoder.encode(userDtoComplete.getPassword()), Role.SELLER); //TODO
    }
    public User getEntityUpdateFromDto(User user, UserDto userDto){
        Stream.of(userDto).forEach((dto)-> {
            if (dto.getEmail() != null) user.setEmail(dto.getEmail());
            if (dto.getName() != null) user.setName(dto.getName());
            if (dto.getPassword() != null) user.setPassword(passwordEncoder.encode(dto.getPassword()));
        });
        return user;
    }
}
