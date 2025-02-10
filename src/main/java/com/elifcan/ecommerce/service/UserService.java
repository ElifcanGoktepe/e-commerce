package com.elifcan.ecommerce.service;

import com.elifcan.ecommerce.dto.request.RegisterUserRequestDto;
import com.elifcan.ecommerce.entity.User;
import com.elifcan.ecommerce.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void registerUser( RegisterUserRequestDto dto) {
        userRepository.save(User.builder()
                        .name(dto.name())
                        .phone(dto.telephone())
                        .email(dto.email())
                        .password(dto.password())
                .build());
    }

}
