package com.LicuadoraProyectoEcommerce.controller.userAuth;
import com.LicuadoraProyectoEcommerce.form.UserRegisterForm;
import com.LicuadoraProyectoEcommerce.dto.userAuth.UserDto;
import com.LicuadoraProyectoEcommerce.form.RefreshTokenForm;
import com.LicuadoraProyectoEcommerce.form.UserLoginForm;
import com.LicuadoraProyectoEcommerce.message.MessageInfo;
import com.LicuadoraProyectoEcommerce.message.UserLoginResponse;
import com.LicuadoraProyectoEcommerce.service.UserAuth.UserAuthService;
import com.LicuadoraProyectoEcommerce.service.UserAuth.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Map;

import static com.LicuadoraProyectoEcommerce.config.MessagesSwagger.*;
@CrossOrigin(origins = "*"
        , methods={RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE}
        ,allowCredentials = "true")
@Tag(name = "Auth User")
@RestController
@RequestMapping("/auth")
public class UserAuthController {

    @Autowired
    private UserAuthService userAuthService;
    @Autowired
    private UserService userService;

    @Operation(summary = "create a new user")
    @PostMapping("/register")
    public ResponseEntity<UserDto> registerUser(@RequestBody @Valid UserRegisterForm userDto){
        return ResponseEntity.status(201).body(userAuthService.registerUser(userDto));
    }
    @Operation(summary = "update a user by id")
    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@Parameter(description = "insert user id", example = "1") @PathVariable String id, @RequestBody @Valid  UserRegisterForm userDto, HttpServletRequest request){
        userAuthService.isTheSameUserLogged(userService.findEntityById(Long.valueOf(id)), request);
        return ResponseEntity.status(201).body(userAuthService.updateUser(Long.valueOf(id), userDto));
    }
    @Operation(summary = "user login")
    @ApiResponse(responseCode = "200", description = "User login and get a token",
            content = { @Content(mediaType = "application/json", examples = {@ExampleObject(value = USER_MESSAGE_TOKEN),                    }) })
    @PostMapping("/login")
    public ResponseEntity<UserLoginResponse> loginUser(@RequestBody @Valid UserLoginForm userLogin, HttpServletRequest request){
        return ResponseEntity.ok(userAuthService.userLogin(userLogin.getEmail(), userLogin.getPassword(), request));
    }
    @Operation(summary = "refresh token")
    @ApiResponse(responseCode = "200", description = "User get refresh token",
            content = { @Content(mediaType = "application/json",examples = {@ExampleObject(value = USER_MESSAGE_REFRESH_TOKEN)}) })
    @PostMapping("/refresh")
    public void refreshToken(@RequestBody @Valid RefreshTokenForm form, HttpServletRequest request, HttpServletResponse response) throws IOException {
        userAuthService.refreshToken(form, request, response);
    }
    @Operation(summary = "delete user by id")
    @ApiResponse(responseCode = "200", description = "User deleted",
            content = { @Content(mediaType = "application/json", examples = {@ExampleObject(value = MESSAGE_DELETE),}) })
    @DeleteMapping("/{id}")
    ResponseEntity<Map<String, String>> deleteUserById(@Parameter(description = "find user by id", example = "1") @PathVariable String id, HttpServletRequest request){
        userAuthService.isTheSameUserLogged(userService.findEntityById(Long.valueOf(id)), request);
        return ResponseEntity.ok(userAuthService.deleteManagerOrSellerByIdUser(Long.valueOf(id)));
    }

    @Operation(summary = "find user by id")
    @GetMapping("/{id}")
    ResponseEntity<UserDto> getById(@Parameter(description = "insert user id", example = "1") @PathVariable String id){
        return ResponseEntity.ok(userService.getUserDtoById(Long.valueOf(id)));
    }
    @GetMapping("/accessDenied")
    ResponseEntity<MessageInfo> accessDenied(HttpServletRequest request){
        return ResponseEntity.status(403).body( new MessageInfo("You are haven't authorization for access this path", 403, request.getRequestURI()));
    }
}