package com.byteworks.servbyte.service;

import com.byteworks.servbyte.exception.CustomException;
import com.byteworks.servbyte.model.*;
import com.byteworks.servbyte.repository.CityRepository;
import com.byteworks.servbyte.repository.RestaurantRepository;
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
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;
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

    private final RestaurantRepository restaurantRepository;

    private final CityRepository cityRepository;

    private final FileStorageService fileStorageService;


    public Map<String, String> login(LoginRequest request) {

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                request.getEmail(), request.getPassword());
        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return Map.of("token", jwtTokenProvider.generateToken(authentication));

    }


    @Transactional
    public Map<String, String> signUp(SignUpRequest request) throws IOException {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new CustomException("email already exist", HttpStatus.CONFLICT);
        }

        if (request.getCompanyType().equals(CompanyType.RESTAURANT)) {
            Restaurant restaurant = Objects.requireNonNull(saveRestaurant(request));
            restaurant.setLogoPicPath(StringUtils.cleanPath(savePicture(request, restaurant)));
        }

        return Map.of("message", "user created");
    }


    private Role getRole() {
        return roleRepository.findByRoleType(RoleType.ROLE_USER).orElseGet(() -> {
            Role userRole = new Role();
            userRole.setRoleType(RoleType.ROLE_USER);
            return roleRepository.save(userRole);
        });
    }


    private Restaurant saveRestaurant(SignUpRequest request) throws IOException {

        Restaurant restaurant = new Restaurant();
        restaurant.setCity(cityRepository.findByName(request.getCity())
                .orElseThrow(() -> new IllegalArgumentException("city doesn't exist")));
        restaurant.setEmail(request.getEmail());
        restaurant.setPassword(passwordEncoder.encode(request.getPassword()));
        restaurant.setName(request.getName());
        restaurant.setRoles(Set.of(getRole()));
        restaurant.setPhoneNumber(request.getPhoneNumber());
        return restaurantRepository.save(restaurant);

    }


    private String savePicture(SignUpRequest request, User user) throws IOException {
        request.setOwnerPath(user.getId().toString());
        return fileStorageService.storeFileToOwnerPath(request);
    }

}
