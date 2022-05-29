package com.example.noticer.repos;

import com.example.noticer.domain.Entry;
import com.example.noticer.domain.Note;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EntriesRepo extends JpaRepository<Entry, Integer> {
    List<Entry> findByNote(Note note);
}
