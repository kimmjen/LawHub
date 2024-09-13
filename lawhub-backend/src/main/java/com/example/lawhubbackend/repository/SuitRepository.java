package com.example.lawhubbackend.repository;

import com.example.lawhubbackend.model.Suit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SuitRepository extends JpaRepository<Suit, Long> {
    List<Suit> findByUserId(Long userId);
}