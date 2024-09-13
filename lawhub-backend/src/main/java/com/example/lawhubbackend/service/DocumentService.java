package com.example.lawhubbackend.service;

import com.example.lawhubbackend.model.Document;
import com.example.lawhubbackend.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class DocumentService {

    @Autowired
    private DocumentRepository documentRepository;

    private final String uploadDirectory = "uploads/";

    // 문서 업로드 처리
    public Document uploadDocument(MultipartFile file, Long suitId) throws IOException {
        Path filePath = Paths.get(uploadDirectory + file.getOriginalFilename());
        Files.write(filePath, file.getBytes());

        Document document = new Document();
        document.setFilePath(filePath.toString());
        // 사건 ID와 연동되는 로직 추가 필요
        return documentRepository.save(document);
    }

    // 특정 문서 조회
    public Document getDocument(Long id) {
        return documentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Document not found"));
    }
}