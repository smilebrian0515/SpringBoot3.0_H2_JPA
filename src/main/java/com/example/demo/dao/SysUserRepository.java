package com.example.demo.dao;


import com.example.demo.dto.entity.SysUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SysUserRepository extends JpaRepository<SysUserEntity, Long> {
}
