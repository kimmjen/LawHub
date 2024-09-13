package com.example.lawhubbackend.repository;

import com.example.lawhubbackend.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findBySuitId(Long suitId);
}
