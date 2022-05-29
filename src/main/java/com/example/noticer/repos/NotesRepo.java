package com.example.noticer.repos;

import com.example.noticer.domain.Note;
import org.springframework.data.jpa.repository.JpaRepository;


public interface NotesRepo extends JpaRepository<Note, Integer> {
    Note findByTitle(String note);
}
