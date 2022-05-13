package com.example.noticer.repos;

import com.example.noticer.domain.MessageTag;
import org.springframework.data.repository.CrudRepository;



public interface MessageTagRepo extends CrudRepository<MessageTag, Integer> {
    MessageTag findByTagName(String tag);
}
