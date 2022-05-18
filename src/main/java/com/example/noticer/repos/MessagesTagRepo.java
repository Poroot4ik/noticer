package com.example.noticer.repos;

import com.example.noticer.domain.MessageTag;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MessagesTagRepo extends JpaRepository<MessageTag, Integer> {
    MessageTag findByTagName(String tag);
}
