package com.example.noticer.repos;

import com.example.noticer.domain.Message;
import com.example.noticer.domain.MessageTag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessagesRepo extends JpaRepository<Message, Integer> {
    List<Message> findByTag(MessageTag tag);
}
