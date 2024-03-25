package com.dux.prueba_tecnica.service.impl;

import com.dux.prueba_tecnica.dto.mapper.UserMapper;
import com.dux.prueba_tecnica.dto.request.UserRequestDTO;
import com.dux.prueba_tecnica.dto.response.AuthResponseDTO;
import com.dux.prueba_tecnica.dto.response.UserResponseDTO;
import com.dux.prueba_tecnica.model.Role;
import com.dux.prueba_tecnica.model.User;
import com.dux.prueba_tecnica.repository.UserRepository;
import com.dux.prueba_tecnica.security.ApplicationConfig;
import com.dux.prueba_tecnica.security.jwt.JwtService;
import com.dux.prueba_tecnica.service.AuthService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final ApplicationConfig securityConfig;
    private final UserMapper userMapper;

    public AuthServiceImpl(UserRepository userRepository, JwtService jwtService,
                           AuthenticationManager authenticationManager, ApplicationConfig securityConfig, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.securityConfig = securityConfig;
        this.userMapper = userMapper;
    }

    @Override
    public UserResponseDTO register(UserRequestDTO userRequestDTO) {
        User objUser = new User.Builder(
                userRequestDTO.getUsername(), securityConfig.passwordEncoder().encode(userRequestDTO.getPassword()), Role.ADMIN)
                .status(true)
                .build();

        userRepository.save(objUser);

        return userMapper.userToResp(objUser);
    }

    @Override
    public AuthResponseDTO login(UserRequestDTO authUserRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authUserRequest.getUsername(), authUserRequest.getPassword()));
        UserDetails user = userRepository.findByUsername(authUserRequest.getUsername()).orElseThrow();
        String token = jwtService.getToken(user);

        AuthResponseDTO authResponseDTO = new AuthResponseDTO();
        authResponseDTO.setToken(token);

        return authResponseDTO;
    }
}
