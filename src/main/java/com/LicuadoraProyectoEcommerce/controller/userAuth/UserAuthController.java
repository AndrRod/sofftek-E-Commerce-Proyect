package com.LicuadoraProyectoEcommerce.controller.userAuth;

import com.LicuadoraProyectoEcommerce.form.UserRegisterForm;
import com.LicuadoraProyectoEcommerce.dto.userAuth.UserDto;
import com.LicuadoraProyectoEcommerce.form.RefreshTokenForm;
import com.LicuadoraProyectoEcommerce.form.RoleNameForm;
import com.LicuadoraProyectoEcommerce.form.UserLoginForm;
import com.LicuadoraProyectoEcommerce.message.MessageInfo;
import com.LicuadoraProyectoEcommerce.message.UserLoginResponse;
import com.LicuadoraProyectoEcommerce.service.UserAuth.UserAuthService;
import com.LicuadoraProyectoEcommerce.service.UserAuth.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class UserAuthController {
    @Autowired
    private UserAuthService userAuthService;
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserDto> registerUser(@RequestBody @Valid UserRegisterForm userDto){
        return ResponseEntity.status(201).body(userAuthService.registerUser(userDto));
    }
    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable String id, @RequestBody @Valid  UserRegisterForm userDto, HttpServletRequest request){
        userAuthService.isTheSameUserLogged(userService.findEntityById(Long.valueOf(id)), request);
        return ResponseEntity.status(201).body(userAuthService.updateUser(Long.valueOf(id), userDto));
    }

    @PostMapping("/login")
    public ResponseEntity<UserLoginResponse> loginUser(@RequestBody @Valid UserLoginForm userLogin, HttpServletRequest request){
        return ResponseEntity.ok(userAuthService.userLogin(userLogin.getEmail(), userLogin.getPassword(), request));
    }
    @PostMapping("/refresh")
    public void refreshToken(@RequestBody @Valid RefreshTokenForm form, HttpServletRequest request, HttpServletResponse response) throws IOException {
        userAuthService.refreshToken(form, request, response);
    }
    @PostMapping("/addrole/{id}")
    public ResponseEntity<MessageInfo> addRoleToUser(@PathVariable String id, @RequestBody @Valid RoleNameForm role, HttpServletRequest request){
        return ResponseEntity.ok(userAuthService.updateUserRol(Long.valueOf(id), role.getRole_name(), request));
    }
    @DeleteMapping("/{id}")
    ResponseEntity<Map<String, String>> deleteUserById(@PathVariable String id, HttpServletRequest request){
        userAuthService.isTheSameUserLogged(userService.findEntityById(Long.valueOf(id)), request);
        return ResponseEntity.ok(userAuthService.deleteManagerOrSellerByIdUser(Long.valueOf(id)));
    }
}