package com.LicuadoraProyectoEcommerce.mapper.userAuth;

import com.LicuadoraProyectoEcommerce.form.UserRegisterForm;
import com.LicuadoraProyectoEcommerce.dto.userAuth.UserDto;
import com.LicuadoraProyectoEcommerce.dto.userAuth.UserDtoComplete;
import com.LicuadoraProyectoEcommerce.model.userAuth.Role;
import com.LicuadoraProyectoEcommerce.model.userAuth.User;
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
    public UserDto getDtoFromEntity(User user){return new UserDto(user.getId(), user.getName(), user.getEmail());}
    public UserDtoComplete getDtoCompleteFromEntity(User user){return new UserDtoComplete(user.getId(), user.getName(), user.getEmail(), user.getPassword(), user.getRole());}
    public List<UserDto> getListDtoFromListEntity(List<User> userList){return userList.stream().map(this::getDtoFromEntity).collect(Collectors.toList());}

    public User getEntityCreateFromDto(UserRegisterForm userRegisterForm){
        return new User(null, userRegisterForm.getName(), userRegisterForm.getEmail(), passwordEncoder.encode(userRegisterForm.getPassword()), Role.NONE);
    }
    public User getEntityUpdateFromDto(User user, UserRegisterForm userDto){
        Stream.of(userDto).forEach((dto)-> {
            if (dto.getEmail() != null) user.setEmail(dto.getEmail());
            if (dto.getName() != null) user.setName(dto.getName());
            if (dto.getPassword() != null) user.setPassword(passwordEncoder.encode(dto.getPassword()));
        });
        return user;
    }
}
