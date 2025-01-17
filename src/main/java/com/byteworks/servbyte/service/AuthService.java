package com.byteworks.servbyte.service;

import com.byteworks.servbyte.exception.CustomException;
import com.byteworks.servbyte.model.*;
import com.byteworks.servbyte.repository.*;
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
import java.util.HashSet;
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

    private final DeliveryCompanyRepository deliveryCompanyRepository;

    private final DeliveryChannelRepository deliveryChannelRepository;

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

        if (request.getCompanyType().equals(CompanyType.DELIVERY_COMPANY)) {
            DeliveryCompany deliveryCompany = Objects.requireNonNull(saveDeliveryCompany(request));
            deliveryCompany.setLogoPicPath(StringUtils.cleanPath(savePicture(request, deliveryCompany)));
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


    private Restaurant saveRestaurant(SignUpRequest request) {

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


    private DeliveryCompany saveDeliveryCompany(SignUpRequest request) {

        DeliveryCompany deliveryCompany = new DeliveryCompany();
        deliveryCompany.setCities(new HashSet<>(cityRepository.findAll()));
        deliveryCompany.setEmail(request.getEmail());
        deliveryCompany.setPassword(passwordEncoder.encode(request.getPassword()));
        deliveryCompany.setName(request.getName());
        deliveryCompany.setRoles(Set.of(getRole()));
        deliveryCompany.setPhoneNumber(request.getPhoneNumber());
        deliveryCompany.setDeliveryChannels(
                new HashSet<>(deliveryChannelRepository.findAllById(request.getDeliveryChannelIds())));
        return deliveryCompanyRepository.save(deliveryCompany);

    }


    private String savePicture(SignUpRequest request, User user) throws IOException {
        request.setOwnerPath(user.getId().toString());
        return fileStorageService.storeFileToOwnerPath(request);
    }

}
