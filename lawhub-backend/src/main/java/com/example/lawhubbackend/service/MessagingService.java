package com.example.lawhubbackend.service;

import com.example.lawhubbackend.model.Message;
import com.example.lawhubbackend.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessagingService {

    @Autowired
    private MessageRepository messageRepository;

    // 특정 사건에 대한 모든 메시지 조회
    public List<Message> getMessagesBySuitId(Long suitId) {
        return messageRepository.findBySuitId(suitId);
    }

    // 메시지 저장 (보내기)
    public Message sendMessage(Message message) {
        return messageRepository.save(message);
    }
}