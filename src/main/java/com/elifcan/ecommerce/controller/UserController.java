package com.elifcan.ecommerce.controller;

import com.elifcan.ecommerce.config.JwtManager;
import com.elifcan.ecommerce.dto.request.AddRoleRequestDto;
import com.elifcan.ecommerce.dto.request.LoginUserDto;
import com.elifcan.ecommerce.dto.request.RegisterUserRequestDto;
import com.elifcan.ecommerce.dto.response.BaseResponse;
import com.elifcan.ecommerce.entity.User;
import com.elifcan.ecommerce.exception.ECommerceException;
import com.elifcan.ecommerce.exception.ErrorType;
import com.elifcan.ecommerce.service.UserRoleService;
import com.elifcan.ecommerce.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static com.elifcan.ecommerce.config.RestApi.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(USER)
@CrossOrigin("*")
@SecurityRequirement(name = "bearerAuth")
public class UserController {

    private final UserService userService;
    private final JwtManager jwtManager;
    private final UserRoleService userRoleService;

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
    private ResponseEntity<BaseResponse<String>> loginUser(@RequestBody @Valid LoginUserDto dto){
        Optional<User> userOptional = userService.findByEmailAndPassword(dto);
        if(userOptional.isEmpty()) throw new ECommerceException(ErrorType.EMAIL_PASSWORD_ERROR);
        return ResponseEntity.ok(BaseResponse.<String>builder()
                        .code(200)
                        .message("Login successful")
                        .data(jwtManager.createToken(userOptional.get().getId()))
                .build());

    }
    @PostMapping("/add-role")
    public ResponseEntity<BaseResponse<Boolean>> addRole(@RequestBody AddRoleRequestDto dto){
        userRoleService.addRole(dto.roleName(), dto.userId());
        return ResponseEntity.ok(BaseResponse.<Boolean>builder()
                .code(200)
                .message("Ok")
                .data(true)
                .build());
    }



}
