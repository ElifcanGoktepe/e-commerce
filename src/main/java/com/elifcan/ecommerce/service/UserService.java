package com.elifcan.ecommerce.service;

import com.elifcan.ecommerce.dto.request.LoginUserDto;
import com.elifcan.ecommerce.dto.request.RegisterUserRequestDto;
import com.elifcan.ecommerce.entity.Product;
import com.elifcan.ecommerce.entity.User;
import com.elifcan.ecommerce.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Optional<User> findByEmailAndPassword(LoginUserDto dto) {
        return userRepository.findByEmailAndPassword(dto.email(), dto.password());
    }


}
