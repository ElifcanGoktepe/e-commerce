package com.elifcan.ecommerce.controller;

import com.elifcan.ecommerce.dto.request.LoginUserDto;
import com.elifcan.ecommerce.dto.request.RegisterUserRequestDto;
import com.elifcan.ecommerce.dto.response.BaseResponse;
import com.elifcan.ecommerce.entity.Product;
import com.elifcan.ecommerce.entity.User;
import com.elifcan.ecommerce.exception.ECommerceException;
import com.elifcan.ecommerce.exception.ErrorType;
import com.elifcan.ecommerce.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.elifcan.ecommerce.congif.RestApi.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(USER)
@CrossOrigin("*")
public class UserController {

    private final UserService userService;

    @PostMapping(REGISTER_USER)
    private ResponseEntity<BaseResponse<Boolean>> registerUser(@RequestBody @Valid RegisterUserRequestDto dto) {
        if(!dto.password().equals(dto.rePassword())) throw new ECommerceException(ErrorType.PASSWORD_ERROR);
        userService.registerUser(dto);
        return ResponseEntity.ok(BaseResponse.<Boolean>builder()
                        .code(200)
                        .message("User registered successfully")
                        .data(true)
                .build());
    }

    @PostMapping(LOGIN_USER)
    private ResponseEntity<BaseResponse<Long>> loginUser(@RequestBody @Valid LoginUserDto dto){
        Optional<User> userOptional = userService.findByEmailAndPassword(dto);
        if(userOptional.isEmpty()) throw new ECommerceException(ErrorType.EMAIL_PASSWORD_ERROR);
        return ResponseEntity.ok(BaseResponse.<Long>builder()
                        .code(200)
                        .message("Login successful")
                        .data(userOptional.get().getId())
                .build());

    }



}
