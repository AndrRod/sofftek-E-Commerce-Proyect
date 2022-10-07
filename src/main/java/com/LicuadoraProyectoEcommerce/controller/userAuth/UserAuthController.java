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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Map;
@Tag(name = "User - Authentication")
@RestController
@RequestMapping("/auth")
public class UserAuthController {
    @Autowired
    private UserAuthService userAuthService;
    @Autowired
    private UserService userService;

    private final String CREATED_OR_UPDATE = "{\n" +
            "    \"id\": 4,\n" +
            "    \"name\": \"Andres Rodriguez\",\n" +
            "    \"email\": \"andres@gmail.com\"\n" +
            "}";
    private final String MESSAGE_OK = "{\n" +
            "    \"message\": \" the entity was deleted by id 1\",\n" +
            "    \"status_code\": 200,\n" +
            "    \"path\": \"http://localhost:8080/auth/4/role/\"\n" +
            "}";
    private final String MESSAGE_DELETE = "{\n" +
            "    \"Message\": \"was deleted success by id 5\"\n" +
            "}";
    @Operation(summary = "create a new user")
    @ApiResponse(responseCode = "200", description = "user created",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDto.class),
                            array = @ArraySchema(schema = @Schema(implementation = String.class)),
                            examples = {
                                    @ExampleObject(value = CREATED_OR_UPDATE),
                            }) })
    @PostMapping("/register")
    public ResponseEntity<UserDto> registerUser(@RequestBody @Valid UserRegisterForm userDto){
        return ResponseEntity.status(201).body(userAuthService.registerUser(userDto));
    }
    @Operation(summary = "update a user by id")
    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable String id, @RequestBody @Valid  UserRegisterForm userDto, HttpServletRequest request){
        userAuthService.isTheSameUserLogged(userService.findEntityById(Long.valueOf(id)), request);
        return ResponseEntity.status(201).body(userAuthService.updateUser(Long.valueOf(id), userDto));
    }
    @Operation(summary = "user login")
    @PostMapping("/login")
    public ResponseEntity<UserLoginResponse> loginUser(@RequestBody @Valid UserLoginForm userLogin, HttpServletRequest request){
        return ResponseEntity.ok(userAuthService.userLogin(userLogin.getEmail(), userLogin.getPassword(), request));
    }
    @Operation(summary = "refresh token")
    @PostMapping("/refresh")
    public void refreshToken(@RequestBody @Valid RefreshTokenForm form, HttpServletRequest request, HttpServletResponse response) throws IOException {
        userAuthService.refreshToken(form, request, response);
    }
    @Operation(summary = "update the user role")
    @PostMapping("/{id}/role")
    public ResponseEntity<MessageInfo> addRoleToUser(@PathVariable String id, @RequestBody @Valid RoleNameForm role, HttpServletRequest request){
        return ResponseEntity.ok(userAuthService.updateUserRol(Long.valueOf(id), role.getRole_name(), request));
    }
    @Operation(summary = "delete user by id")
    @ApiResponse(responseCode = "200", description = "User deleted",
            content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Map.class),
                    array = @ArraySchema(schema = @Schema(implementation = String.class)),
                    examples = {
                            @ExampleObject(value = MESSAGE_DELETE),
                    }) })
    @DeleteMapping("/{id}")
    ResponseEntity<Map<String, String>> deleteUserById(@PathVariable String id, HttpServletRequest request){
        userAuthService.isTheSameUserLogged(userService.findEntityById(Long.valueOf(id)), request);
        return ResponseEntity.ok(userAuthService.deleteManagerOrSellerByIdUser(Long.valueOf(id)));
    }
}