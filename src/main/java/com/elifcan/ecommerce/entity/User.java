package com.elifcan.ecommerce.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbluser")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(nullable = false, length = 80)
    String name;
    String address;
    @Column(nullable = false, length = 20)
    String phone;
    @Column(nullable = false, unique = true)
    String email;
    @Column(nullable = false, length = 128)
    String password;
    String avatar;
}
