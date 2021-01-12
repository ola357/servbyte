package com.byteworks.servbyte.service;

import com.byteworks.servbyte.exception.CustomException;
import com.byteworks.servbyte.model.Role;
import com.byteworks.servbyte.model.RoleType;
import com.byteworks.servbyte.model.User;
import com.byteworks.servbyte.repository.RoleRepository;
import com.byteworks.servbyte.repository.UserRepository;
import com.byteworks.servbyte.request.LoginRequest;
import com.byteworks.servbyte.request.SignUpRequest;
import com.byteworks.servbyte.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;


@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final AuthenticationManager authenticationManager;

    private final PasswordEncoder passwordEncoder;

    private final JwtTokenProvider jwtTokenProvider;

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;


    public Map<String, String> login(LoginRequest request) {

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                request.getEmail(), request.getPassword());
        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return Map.of("token", jwtTokenProvider.generateToken(authentication));

    }


    public Map<String, String> signUp(SignUpRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new CustomException("email already exist", HttpStatus.CONFLICT);
        }

        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setName(request.getName());

        Role role = roleRepository.findByRoleType(RoleType.ROLE_USER).orElseGet(() -> {
            Role userRole = new Role();
            userRole.setRoleType(RoleType.ROLE_USER);
            return roleRepository.save(userRole);
        });

        user.setRoles(Set.of(role));

        userRepository.save(user);
        return Map.of("message", "new user created");
    }

}
