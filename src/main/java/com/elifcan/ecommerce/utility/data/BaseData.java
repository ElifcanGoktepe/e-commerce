package com.elifcan.ecommerce.utility.data;

import com.elifcan.ecommerce.entity.User;
import com.elifcan.ecommerce.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class BaseData {
    private final UserRepository userRepository;

    @PostConstruct
    public void init(){
        log.error("Hata error log");
        log.warn("log warn");
        log.info("log info");
        log.debug("log debug");
        log.trace("log trace");

    }

    private void kullaniciEkle(){
        User user = User.builder()
                .name("demet")
                .email("demet@gmail.com")
                .password("12345678")
                .phone("0 666 999 8877")
                .address("İzmir")
                .avatar("https://resim.png")
                .build();
        userRepository.save(user);
    }

    private void addCategory(){}

    private void addProduct(){}
}