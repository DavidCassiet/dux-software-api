package com.dux.prueba_tecnica.service;

import com.dux.prueba_tecnica.dto.request.UserRequestDTO;
import com.dux.prueba_tecnica.dto.response.AuthResponseDTO;
import com.dux.prueba_tecnica.dto.response.UserResponseDTO;

public interface AuthService {
    UserResponseDTO register(UserRequestDTO userRequestDTO);

    AuthResponseDTO login(UserRequestDTO authUserRequest);
}
