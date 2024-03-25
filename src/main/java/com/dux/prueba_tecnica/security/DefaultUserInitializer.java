package com.dux.prueba_tecnica.security;

import com.dux.prueba_tecnica.model.Role;
import com.dux.prueba_tecnica.model.User;
import com.dux.prueba_tecnica.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DefaultUserInitializer {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public DefaultUserInitializer(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @PostConstruct
    public void initDefaultUser() {
        if (userRepository.findByUsername("test").isEmpty()) {
            User user = new User.Builder(
                    "test",
                    passwordEncoder.encode("12345"),
                    Role.ADMIN)
                    .status(true)
                    .build();

            userRepository.save(user);
        }
    }
}

