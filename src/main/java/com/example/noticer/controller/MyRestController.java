package com.example.noticer.controller;

import com.example.noticer.domain.Message;
import com.example.noticer.repos.MessageRepo;
import com.example.noticer.repos.MessageTagRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MyRestController {
    @Autowired
    private MessageRepo messageRepo;

    @Autowired
    private MessageTagRepo messageTagRepo;

    @DeleteMapping("/delete/{id}")
    void deleteMessage(@PathVariable Integer id) {
        messageRepo.deleteById(id);
    }
}
