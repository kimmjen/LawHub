package com.example.lawhubbackend.controller;

import com.example.lawhubbackend.model.Suit;
import com.example.lawhubbackend.repository.SuitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/suits")
public class SuitController {

    @Autowired
    private SuitRepository suitRepository;

    @GetMapping
    public List<Suit> getAllSuits(){
        return suitRepository.findAll();
    }

    @PostMapping
    public Suit createSuit(@RequestBody Suit suit){
        return suitRepository.save(suit);
    }

    @PutMapping("/{id}")
    public Suit updateSuit(@PathVariable Long id, @RequestBody Suit updatedSuit) {
        Suit existingSuit = suitRepository.findById(id).orElseThrow();
        existingSuit.setStatus(updatedSuit.getStatus());
        return suitRepository.save(existingSuit);
    }

}
