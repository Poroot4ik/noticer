package com.example.noticer.repos;

import com.example.noticer.domain.Message;
import com.example.noticer.domain.MessageTag;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MessageRepo extends CrudRepository<Message, Integer> {
    List<Message> findByTag(MessageTag tag);
}
