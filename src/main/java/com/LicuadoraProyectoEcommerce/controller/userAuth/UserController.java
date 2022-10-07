package com.LicuadoraProyectoEcommerce.controller.userAuth;

import com.LicuadoraProyectoEcommerce.form.UserRegisterForm;
import com.LicuadoraProyectoEcommerce.dto.userAuth.UserDto;
import com.LicuadoraProyectoEcommerce.dto.userAuth.UserDtoComplete;
import com.LicuadoraProyectoEcommerce.service.UserAuth.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
@Tag(name = "User")
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    ResponseEntity<UserDtoComplete> getById(@PathVariable String id){
        return ResponseEntity.ok(userService.getById(Long.valueOf(id)));
    }
    @PostMapping
    ResponseEntity<UserDto> createEntity(@RequestBody UserRegisterForm userDto){
        return ResponseEntity.status(201).body(userService.createEntity(userDto));
    }
    @GetMapping
    ResponseEntity<List<UserDto>> getEntityListPagination(@RequestParam String page){
        return ResponseEntity.ok(userService.getUserListPagination(Integer.valueOf(page)));
    }
    @PutMapping("/{id}")
    ResponseEntity<UserDto> updateEntity(@PathVariable String id, @RequestBody UserRegisterForm userDto){
        return ResponseEntity.ok(userService.updateEntity(Long.valueOf(id), userDto));
    }
    @DeleteMapping("/{id}")
    ResponseEntity<Map<String, String>> deleteById(@PathVariable String id){
        return ResponseEntity.ok(userService.deleteById(Long.valueOf(id)));
    }
}
