package com.example.demo.dto.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "SysUser")
public class SysUserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
}
