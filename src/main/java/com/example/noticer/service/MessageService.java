package com.example.noticer.service;

import com.example.noticer.domain.Message;
import com.example.noticer.domain.MessageTag;
import com.example.noticer.repos.MessagesRepo;
import com.example.noticer.repos.MessagesTagRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class MessageService {

    private final MessagesRepo messagesRepo;
    private final MessagesTagRepo messagesTagRepo;

    @Autowired
    public MessageService(MessagesRepo messagesRepo, MessagesTagRepo messagesTagRepo) {
        this.messagesRepo = messagesRepo;
        this.messagesTagRepo = messagesTagRepo;
    }

    public List<Message> findAll() {
        return messagesRepo.findAll();
    }

    public Message findOne(Integer id) {
        Optional<Message> foundMessage = messagesRepo.findById(id);
        return foundMessage.orElse(null);
    }

    @Transactional
    public void save(Message message) {

        MessageTag tag = messagesTagRepo.findByTagName(message.getTagName());
        if (tag != null) {
            message.setTag(tag);
        }

        messagesRepo.save(message);
    }

    @Transactional
    public void update(Message message) {

        MessageTag tag = messagesTagRepo.findByTagName(message.getTagName());
        if (tag != null) {
            message.setTag(tag);
        }

        messagesRepo.save(message);
    }

    @Transactional
    public void delete(Integer id) {

        Optional<Message> message = messagesRepo.findById(id);
        MessageTag messageTag = message.get().getTag();
        messagesRepo.deleteById(id);
        List<Message> messages = messagesRepo.findByTag(messageTag);
        if (messages.isEmpty()) {
            messagesTagRepo.deleteById(messageTag.getId());
        }
    }
}
