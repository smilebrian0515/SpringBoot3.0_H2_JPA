package com.example.demo.controller;


import com.example.demo.dao.SysUserRepository;
import com.example.demo.dto.entity.SysUserEntity;
import com.example.demo.shared.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final SysUserRepository userRepository;

    public UserController(SysUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // 取得所有使用者
    @GetMapping
    public ResponseEntity<ApiResponse> getAllUsers() {
        List<SysUserEntity> all = userRepository.findAll();
        return ApiResponse.newInstance().success(all);
    }

    // 依 ID 取得使用者
    @GetMapping("/{id}")
    public ResponseEntity<SysUserEntity> getUserById(@PathVariable Long id) {
        return userRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 新增使用者
    @PostMapping
    public SysUserEntity createUser(@RequestBody SysUserEntity user) {
        return userRepository.save(user);
    }

    // 更新使用者
    @PutMapping("/{id}")
    public ResponseEntity<SysUserEntity> updateUser(@PathVariable Long id, @RequestBody SysUserEntity userDetails) {
        return userRepository.findById(id).map(user -> {
            user.setName(userDetails.getName());
            user.setEmail(userDetails.getEmail());
            return ResponseEntity.ok(userRepository.save(user));
        }).orElse(ResponseEntity.notFound().build());
    }

    // 刪除使用者
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable Long id) {
        return userRepository.findById(id).map(user -> {
            userRepository.delete(user);
            return ResponseEntity.ok().build();
        }).orElse(ResponseEntity.notFound().build());
    }
}
