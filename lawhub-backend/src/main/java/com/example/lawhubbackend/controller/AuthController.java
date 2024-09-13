package com.example.lawhubbackend.controller;

import com.example.lawhubbackend.dto.UserResponse;
import com.example.lawhubbackend.model.User;
import com.example.lawhubbackend.repository.UserRepository;
import com.example.lawhubbackend.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody User user) {
        // 사용자 비밀번호를 암호화
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        // 사용자 정보를 데이터베이스에 저장
        User savedUser = userRepository.save(user);

        // 저장된 사용자 정보를 리턴
        return ResponseEntity.status(HttpStatus.CREATED).body(new UserResponse(
                savedUser.getId(),
                savedUser.getUsername(),
                savedUser.getEmail(),
                savedUser.getPhone()
        ));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
        Optional<User> userOptional = userRepository.findByUsername(credentials.get("username"));
        if (userOptional.isEmpty()) {
            return ResponseEntity.status(404).body("User not found");
        }

        User user = userOptional.get();
        if (passwordEncoder.matches(credentials.get("password"), user.getPassword())) {
            // 권한 목록을 Set에서 List로 변환하여 JWT 토큰을 생성
            String token = jwtTokenProvider.createToken(user.getUsername(), new ArrayList<>(user.getRoles()));
            return ResponseEntity.ok(Map.of("token", token));
        } else {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }
}
