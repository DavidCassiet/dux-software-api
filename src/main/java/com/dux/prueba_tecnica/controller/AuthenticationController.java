package com.dux.prueba_tecnica.controller;

import com.dux.prueba_tecnica.dto.request.UserRequestDTO;
import com.dux.prueba_tecnica.dto.response.AuthResponseDTO;
import com.dux.prueba_tecnica.dto.response.UserResponseDTO;
import com.dux.prueba_tecnica.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private final AuthService authService;

    public AuthenticationController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody UserRequestDTO userRequestDTO) {
        return new ResponseEntity<>(authService.login(userRequestDTO), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(@RequestBody UserRequestDTO userRequestDTO) {
        return new ResponseEntity<>(authService.register(userRequestDTO), HttpStatus.OK);
    }
}
