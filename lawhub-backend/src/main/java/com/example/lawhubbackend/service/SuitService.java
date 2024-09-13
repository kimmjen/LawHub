package com.example.lawhubbackend.service;


import com.example.lawhubbackend.model.Suit;
import com.example.lawhubbackend.model.SuitStatus;
import com.example.lawhubbackend.repository.SuitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SuitService {

    @Autowired
    private SuitRepository suitRepository;

    // 모든 사건 조회
    public List<Suit> getAllSuits() {
        return suitRepository.findAll();
    }

    // 사건 생성
    public Suit createSuit(Suit suit) {
        suit.setStatus(SuitStatus.IN_PROGRESS); // 사건 상태를 기본값으로 설정
        return suitRepository.save(suit);
    }

    // 사건 업데이트
    public Suit updateSuit(Long id, Suit updatedSuit) {
        Optional<Suit> suitOptional = suitRepository.findById(id);
        if (suitOptional.isPresent()) {
            Suit existingSuit = suitOptional.get();
            existingSuit.setTitle(updatedSuit.getTitle());
            existingSuit.setDescription(updatedSuit.getDescription());
            existingSuit.setStatus(updatedSuit.getStatus());
            return suitRepository.save(existingSuit);
        } else {
            throw new IllegalArgumentException("Suit not found");
        }
    }

    // 사건 상태 변경
    public Suit changeSuitStatus(Long id, SuitStatus status) {
        Suit suit = suitRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Suit not found"));
        suit.setStatus(status);
        return suitRepository.save(suit);
    }
}