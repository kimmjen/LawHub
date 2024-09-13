package com.example.lawhubbackend.controller;

import com.example.lawhubbackend.model.Document;
import com.example.lawhubbackend.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/documents")
public class DocumentController {

    @Autowired
    private DocumentRepository documentRepository;

    private final String uploadDirectory = "uploads/";

    @PostMapping("/upload")
    public Document uploadDocument(@RequestParam("file") MultipartFile file) throws Exception {
        Path filePath = Paths.get(uploadDirectory + file.getOriginalFilename());
        Files.write(filePath, file.getBytes());

        Document document = new Document();
        document.setFilePath(filePath.toString());
        return documentRepository.save(document);
    }

    @GetMapping("/{id}")
    public Document getDocument(@PathVariable Long id) {
        return documentRepository.findById(id).orElseThrow();
    }
}
