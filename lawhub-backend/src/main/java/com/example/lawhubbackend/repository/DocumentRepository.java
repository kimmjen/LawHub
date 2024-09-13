package com.example.lawhubbackend.repository;

import com.example.lawhubbackend.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository<Document, Long> {
}
