package com.example.lawhubbackend.controller;

import com.example.lawhubbackend.model.Message;
import com.example.lawhubbackend.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/messages")
public class MessagingController {

    @Autowired
    private MessageRepository messageRepository;

    @GetMapping("/suit/{suitId}")
    public List<Message> getMessagesBySuitId(@PathVariable Long suitId) {
        return messageRepository.findBySuitId(suitId);
    }

    @PostMapping
    public Message sendMessage(@RequestBody Message message) {
        return messageRepository.save(message);
    }
}
