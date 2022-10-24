package com.LicuadoraProyectoEcommerce.controller.userAuth;

import com.LicuadoraProyectoEcommerce.form.RoleNameForm;
import com.LicuadoraProyectoEcommerce.form.UserRegisterForm;
import com.LicuadoraProyectoEcommerce.dto.userAuth.UserDto;
import com.LicuadoraProyectoEcommerce.dto.userAuth.UserDtoComplete;
import com.LicuadoraProyectoEcommerce.message.MessageInfo;
import com.LicuadoraProyectoEcommerce.service.UserAuth.UserAuthService;
import com.LicuadoraProyectoEcommerce.service.UserAuth.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

import static com.LicuadoraProyectoEcommerce.config.MessagesSwagger.*;

@Tag(name = "Admin User")
@RestController
@RequestMapping("/admin/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserAuthService userAuthService;


    @Operation(summary = "find user by id")
    @GetMapping("/{id}")
    ResponseEntity<UserDtoComplete> getById(@Parameter(description = "find user by id", example = "1") @PathVariable String id){
        return ResponseEntity.ok(userService.getById(Long.valueOf(id)));
    }
    @Operation(summary = "create user")
    @PostMapping
    ResponseEntity<UserDto> createEntity(@RequestBody UserRegisterForm userDto){
        return ResponseEntity.status(201).body(userService.createEntity(userDto));
    }
    @Operation(summary = "get a user list of ten by page")
    @GetMapping
    ResponseEntity<List<UserDto>> getEntityListPagination(@Parameter(description = "get a list by page", example = "0") @RequestParam(defaultValue = "0", required = false) String page){
        return ResponseEntity.ok(userService.getUserListPagination(Integer.valueOf(page)));
    }
    @Operation(summary = "user update by id")
    @PutMapping("/{id}")
    ResponseEntity<UserDto> updateEntity(@Parameter(description = "find user by id", example = "1")  @PathVariable String id, @RequestBody UserRegisterForm userDto){
        return ResponseEntity.ok(userService.updateEntity(Long.valueOf(id), userDto));
    }
    @Operation(summary = "delete user by id")
    @ApiResponse(responseCode = "200", description = "User deleted",
            content = { @Content(mediaType = "application/json",examples = {@ExampleObject(value = MESSAGE_DELETE)}) })
    @DeleteMapping("/{id}")
    ResponseEntity<Map<String, String>> deleteById(@Parameter(description = "find user by id", example = "1") @PathVariable String id){
        return ResponseEntity.ok(userService.deleteById(Long.valueOf(id)));
    }

    @Operation(summary = "update the user role")
    @ApiResponse(responseCode = "200", description = "User Role update ",
            content = { @Content(mediaType = "application/json", examples = {@ExampleObject(value = USER_MESSAGE_UPDATE_ROLE),}) })
    @PutMapping("/{id}/role")
    public ResponseEntity<MessageInfo> addRoleToUser(@Parameter(description = "find user by id", example = "1") @PathVariable String id, @RequestBody @Valid RoleNameForm role, HttpServletRequest request){
        return ResponseEntity.ok(userAuthService.updateUserRol(Long.valueOf(id), role.getRole_name(), request));
    }
}
