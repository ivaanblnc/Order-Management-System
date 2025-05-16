package com.ivan.Order_Management_System.users.service;
import com.ivan.Order_Management_System.auth.dto.DTOUser;
import com.ivan.Order_Management_System.auth.model.User;
import com.ivan.Order_Management_System.users.repository.UserOptionsRepository;
import com.ivan.Order_Management_System.users.InvalidUserDataException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserOptions {

    private final UserOptionsRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DTOUser getUserProfile(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
        return new DTOUser(user.getUsername(), user.getEmail(), user.getFullName(), user.getRole().getName(), null);    }

    public ResponseEntity<?> updateUserInfo(String username, DTOUser updatedUser) {
        try {
            User user = userRepository.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

            if (updatedUser.getEmail() == null || !updatedUser.getEmail().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
                throw new InvalidUserDataException("Email no válido");
            }
            userRepository.findByEmail(updatedUser.getEmail())
                    .ifPresent(existingUser -> {
                        if (!existingUser.getUsername().equals(username)) {
                            throw new InvalidUserDataException("Email ya registrado por otro usuario");
                        }
                    });

            if (updatedUser.getUsername() != null && !updatedUser.getUsername().equals(username)) {
                if (userRepository.existsByUsername(updatedUser.getUsername())) {
                    throw new InvalidUserDataException("Nombre de usuario ya en uso");
                }
                user.setUsername(updatedUser.getUsername());
            }

            if (updatedUser.getPassword() != null && !updatedUser.getPassword().isEmpty()) {
                if (updatedUser.getPassword().length() < 8) {
                    throw new InvalidUserDataException("La contraseña debe tener mínimo 8 caracteres");
                }
                user.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
            }

            if (updatedUser.getFullName() == null || updatedUser.getFullName().trim().isEmpty()) {
                throw new InvalidUserDataException("Nombre completo no puede estar vacío");
            }

            user.setEmail(updatedUser.getEmail());
            user.setFullName(updatedUser.getFullName());
            userRepository.save(user);
            return ResponseEntity.ok(Map.of("message", "Usuario actualizado correctamente"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", e.getMessage()));
        }
    }

    public ResponseEntity<?> deleteUser(String username) {
        try {
            User user = userRepository.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
            userRepository.delete(user);
            return ResponseEntity.ok(Map.of("message", "Usuario eliminado correctamente"));
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", "Error al eliminar el usuario"));
        }
    }
}