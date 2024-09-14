package com.example.lawhubbackend.service;

import com.example.lawhubbackend.model.User;
import com.example.lawhubbackend.repository.UserRepository;
import com.example.lawhubbackend.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    // 회원 가입 로직
    public String registerUser(User user) {
        // 패스워드를 암호화하고 저장
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "User registered successfully";
    }

    // 로그인 로직
    public String loginUser(String username, String password) {
        // 사용자 조회
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            // 패스워드 검증
            if (passwordEncoder.matches(password, user.getPassword())) {
                // JWT 토큰 발급 (단일 역할을 리스트로 변환하여 전달)
                return jwtTokenProvider.createToken(user.getUsername(), user.getRole());
            }
        }
        throw new IllegalArgumentException("Invalid username or password");
    }
}
