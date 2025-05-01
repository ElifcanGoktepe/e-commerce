package com.elifcan.ecommerce.config;

import com.elifcan.ecommerce.entity.User;
import com.elifcan.ecommerce.entity.UserRole;
import com.elifcan.ecommerce.service.UserService;
import com.elifcan.ecommerce.service.UserRoleService;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JwtUserDetails implements UserDetailsService {

    private final UserService userService;
    private final UserRoleService userRoleService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }

    public UserDetails loadUserById(Long userId) {
        // 1. adım bu id ye sahip bir kullanıcı var mı?
        Optional<User> user = userService.findByUserId(userId);
        if(user.isEmpty()) return null;
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        // user role servisinden userId si,ne ait rollerin listesini çelkiyoruz.
        List<UserRole> roleLists = userRoleService.findAllRole(userId);
        // bu rol listesini GrantedAuthority içerisine ekliyoruz.
        roleLists.forEach(r->{
            grantedAuthorities.add(new SimpleGrantedAuthority(r.getRoleName()));
        });

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.get().getEmail())
                .password(user.get().getPassword())
                .accountLocked(false)
                .accountExpired(false)
                .authorities(grantedAuthorities)
                .build();
    }
}