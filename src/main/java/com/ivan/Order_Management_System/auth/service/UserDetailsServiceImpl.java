package com.ivan.Order_Management_System.auth.service;

import com.ivan.Order_Management_System.auth.dto.DTOUser;
import com.ivan.Order_Management_System.auth.model.User;
import com.ivan.Order_Management_System.auth.model.Role;
import com.ivan.Order_Management_System.auth.repository.RoleRepository;
import com.ivan.Order_Management_System.auth.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserDetailsServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole().getName()))
        );
    }

    @Transactional
    public User registerUser(DTOUser newUser) {
        if (userRepository.existsByEmail(newUser.getEmail())) {
            throw new IllegalArgumentException("El email ya está en uso");
        }
        if (userRepository.existsByUsername(newUser.getUsername())) {
            throw new IllegalArgumentException("El nombre de usuario ya está en uso");
        }
        if (newUser.getPassword() == null || newUser.getPassword().length() < 8) {
            throw new IllegalArgumentException("La contraseña debe tener al menos 8 caracteres");
        }

        Role role = roleRepository.findByName(newUser.getRoleName())
                .orElseThrow(() -> new IllegalArgumentException("Rol no válido"));

        User user = new User();
        user.setUsername(newUser.getUsername());
        user.setEmail(newUser.getEmail());
        user.setFullName(newUser.getFullName());
        user.setRole(role);
        user.setPassword(passwordEncoder.encode(newUser.getPassword()));
        user.setState("ACTIVE");
        user.setCreatedAt(java.time.LocalDateTime.now());

        return userRepository.save(user);
    }
}
