package com.example.noticer.service;

import com.example.noticer.domain.Note;
import com.example.noticer.repos.EntriesRepo;
import com.example.noticer.repos.NotesRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class NoteService {

    private final NotesRepo notesRepo;
    private final EntriesRepo entriesRepo;

    public NoteService(NotesRepo NotesRepo, EntriesRepo entriesRepo) {
        this.notesRepo = NotesRepo;
        this.entriesRepo = entriesRepo;
    }

    public List<Note> findAll() {
        return notesRepo.findAll();
    }

    public Note findOne(Integer id) {
        Optional<Note> foundNote = notesRepo.findById(id);
        return foundNote.orElse(null);
    }

    @Transactional
    public void save(Note note) {
        notesRepo.save(note);
    }

    @Transactional
    public void update(Note note) {
        notesRepo.save(note);
    }

    @Transactional
    public void delete(Integer id) {
        notesRepo.deleteById(id);
    }
}
