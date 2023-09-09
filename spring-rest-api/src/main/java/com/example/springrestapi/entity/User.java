package com.example.springrestapi.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "tbl_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="name", nullable = false, columnDefinition = "varchar(100)")
    private String name;
    @Column(name="email", nullable = false, unique = true)
    private String email;
    private String phone;
    private String address;
    private String avatar;
    private String password;
}
