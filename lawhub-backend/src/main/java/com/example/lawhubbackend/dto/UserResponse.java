package com.example.lawhubbackend.dto;

import java.util.Set;

public class UserResponse {

    private Long id;
    private String username;
    private String email;
    private String phone;
    private String role;  // 단일 역할을 위한 필드

    // 생성자
    public UserResponse(Long id, String username, String email, String phone, String role) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.role = role;
    }

    // Getters와 Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getRole() {
        return role;
    }
}
