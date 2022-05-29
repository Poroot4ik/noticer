package com.example.noticer.service;

import com.example.noticer.domain.Entry;
import com.example.noticer.domain.Note;
import com.example.noticer.repos.EntriesRepo;
import com.example.noticer.repos.NotesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class EntryService {

    private final EntriesRepo entriesRepo;
    private final NotesRepo NotesRepo;

    @Autowired
    public EntryService(EntriesRepo entriesRepo, NotesRepo NotesRepo) {
        this.entriesRepo = entriesRepo;
        this.NotesRepo = NotesRepo;
    }

    public List<Entry> findAll() {
        return entriesRepo.findAll();
    }

    public Entry findOne(Integer id) {
        Optional<Entry> foundEntry = entriesRepo.findById(id);
        return foundEntry.orElse(null);
    }

    @Transactional
    public void save(Entry entry) {

        Note note = NotesRepo.findByTitle(entry.getTitle());
        if (note != null) {
            entry.setNote(note);
        }

        entriesRepo.save(entry);
    }

    @Transactional
    public void update(Entry entry) {

        Note note = NotesRepo.findByTitle(entry.getTitle());
        if (note != null) {
            entry.setNote(note);
        }

        entriesRepo.save(entry);
    }

    @Transactional
    public void delete(Integer id) {

        Optional<Entry> entry = entriesRepo.findById(id);
        Note Note = entry.get().getNote();
        entriesRepo.deleteById(id);
        List<Entry> entries = entriesRepo.findByNote(Note);
        if (entries.isEmpty()) {
            NotesRepo.deleteById(Note.getId());
        }
    }
}
