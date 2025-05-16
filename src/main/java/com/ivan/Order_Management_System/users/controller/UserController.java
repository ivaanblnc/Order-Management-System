package com.ivan.Order_Management_System.users.controller;


import com.ivan.Order_Management_System.auth.dto.DTOUser;
import com.ivan.Order_Management_System.users.service.UserOptions;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserOptions userDetailsService;

    @GetMapping("/me")
    public ResponseEntity<DTOUser> getUserProfile(Authentication auth) {
        String username = auth.getName();
        DTOUser user = userDetailsService.getUserProfile(username);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/update")
    public ResponseEntity<Map<String, String>> updateUser(@RequestBody DTOUser updatedUser, Authentication auth) {
        String username = auth.getName();
        userDetailsService.updateUserInfo(username, updatedUser);
        return ResponseEntity.ok(Map.of("message", "Usuario actualizado correctamente"));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Map<String, String>> deleteUser(Authentication auth) {
        String username = auth.getName();
        userDetailsService.deleteUser(username);
        return ResponseEntity.ok(Map.of("message", "Cuenta eliminada correctamente"));
    }

    @GetMapping("/{username}")
    @PreAuthorize("hasRole('ADMIN') or #username == authentication.principal.username")
    public ResponseEntity<DTOUser> getUser(@PathVariable String username) {
        DTOUser user = userDetailsService.getUserProfile(username);
        return ResponseEntity.ok(user);
    }
}