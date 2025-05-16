package com.ivan.Order_Management_System.auth.controller;

import com.ivan.Order_Management_System.auth.dto.DTOUser;
import com.ivan.Order_Management_System.auth.dto.DTOUserLogin;
import com.ivan.Order_Management_System.auth.service.UserDetailsServiceImpl;
import com.ivan.Order_Management_System.security.jwt.DTOJwtResponse;
import com.ivan.Order_Management_System.security.jwt.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserDetailsServiceImpl userDetailsService;

    public AuthController(AuthenticationManager authenticationManager, JwtService jwtService, UserDetailsServiceImpl userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> registerAuth(@RequestBody DTOUser registerUser) {
        try {
            userDetailsService.registerUser(registerUser);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Usuario registrado correctamente");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "Error al registrar usuario: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }

    @GetMapping("/login")
    public ResponseEntity<DTOJwtResponse> login(@RequestBody DTOUserLogin loginRequest)
    {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );

        String jwtToken = jwtService.generateToken(loginRequest.getUsername());

        DTOJwtResponse jwtResponse = new DTOJwtResponse(jwtToken, loginRequest.getUsername());

        return ResponseEntity.ok(jwtResponse);
    }



}
