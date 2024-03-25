package com.dux.prueba_tecnica.dto.mapper;

import com.dux.prueba_tecnica.dto.request.UserRequestDTO;
import com.dux.prueba_tecnica.dto.response.UserResponseDTO;
import com.dux.prueba_tecnica.model.User;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;


@Component
@Mapper(componentModel = "spring")
public interface UserMapper {

    User reqToUser(UserRequestDTO userRequestDTO);

    UserResponseDTO userToResp(User user);
}
