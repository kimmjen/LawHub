package com.example.lawhubbackend.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Suit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String title;
    private String description;

    @Enumerated(EnumType.STRING)
    private SuitStatus status; // 사건 상태 (IN_PROGRESS, COMPLETED, CANCELED)

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user; // 사건 담당 변호사

    private Date createdAt;
    private Date updatedAt;

    // Getters and Setters

    // ID 필드에 대한 Getter와 Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Title 필드에 대한 Getter와 Setter
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    // Description 필드에 대한 Getter와 Setter
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Status 필드에 대한 Getter와 Setter
    public SuitStatus getStatus() {
        return status;
    }

    public void setStatus(SuitStatus status) {
        this.status = status;
    }

    // User 필드에 대한 Getter와 Setter (담당 변호사)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    // CreatedAt 필드에 대한 Getter와 Setter
    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    // UpdatedAt 필드에 대한 Getter와 Setter
    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}

