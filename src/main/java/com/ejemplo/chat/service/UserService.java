package com.ejemplo.chat.service;

import com.ejemplo.chat.model.Role;
import com.ejemplo.chat.model.User;
import com.ejemplo.chat.repository.RoleRepository;
import com.ejemplo.chat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerUser(String username, String password, String email) {
        if (userRepository.existsByUsername(username)) {
            throw new RuntimeException("El nombre de usuario ya existe");
        }

        if (userRepository.existsByEmail(email)) {
            throw new RuntimeException("El email ya está registrado");
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setEmail(email);

        // Asignar rol USER por defecto
        Role userRole = roleRepository.findByName("USER")
                .orElseGet(() -> roleRepository.save(new Role("USER")));

        user.setRoles(new HashSet<>());
        user.getRoles().add(userRole);

        return userRepository.save(user);
    }
}