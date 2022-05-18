package com.example.noticer.service;

import com.example.noticer.domain.Message;
import com.example.noticer.domain.MessageTag;
import com.example.noticer.repos.MessagesTagRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class MessageTagService {

    private final MessagesTagRepo messagesTagRepo;

    public MessageTagService(MessagesTagRepo messagesTagRepo) {
        this.messagesTagRepo = messagesTagRepo;
    }

    public List<MessageTag> findAll() {
        return messagesTagRepo.findAll();
    }

    public MessageTag findOne(Integer id) {
        Optional<MessageTag> foundTag = messagesTagRepo.findById(id);
        return foundTag.orElse(null);
    }

    @Transactional
    public void save(MessageTag tag) {
        messagesTagRepo.save(tag);
    }

    @Transactional
    public void update(MessageTag tag) {
        messagesTagRepo.save(tag);
    }

    @Transactional
    public void delete(Integer id) {
        messagesTagRepo.deleteById(id);
    }
}
