package com.example.noticer.controller;

import com.example.noticer.domain.Entry;
import com.example.noticer.domain.Note;
import com.example.noticer.service.EntryService;
import com.example.noticer.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;
@Controller
@RequestMapping("/notes")
public class NoteController {

    private final NoteService noteService;
    private final EntryService entryService;

    @Autowired
    public NoteController(NoteService noteService, EntryService entryService) {
        this.noteService = noteService;
        this.entryService = entryService;
    }

    @GetMapping()
    public String index(Map<String, Object> model) {
        model.put("notes", noteService.findAll());
        return "notes/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable(value="id") Integer id, @ModelAttribute(value="newEntry") Entry newEntry, Map<String, Object> model) {
        Note note = noteService.findOne(id);
        newEntry.setNote(note);
        model.put("note", note);
        return "notes/show";
    }

    @GetMapping("/new")
    public String newEntry(@ModelAttribute(value="note") Note note, @ModelAttribute(value="newEntry") Entry newEntry, Map<String, Object> model) {
        return "notes/new";
    }

    @PostMapping()
    public String create(@ModelAttribute(value="note") @Valid Note note, @ModelAttribute(value="newEntry") Entry newEntry,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "notes/new";
        noteService.save(note);
        newEntry.setNote(note);
        if (!newEntry.getText().isEmpty()) {
            entryService.save(newEntry);
        }
        return "redirect:/notes/" + note.getId();
    }

//    @GetMapping("/{id}/edit")
//    public String edit(Map<String, Object> model, @PathVariable("id") Integer id, @ModelAttribute(value="newEntry") Entry newEntry) {
//        model.put("note", noteService.findOne(id));
//        return "redirect:/notes/" + id;
//    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute(value="note") @Valid Note note, @ModelAttribute(value="newEntry") @Valid Entry newEntry,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "redirect:/notes/"+ note.getId();

        noteService.update(note);
        if (!newEntry.getText().isEmpty()) {
            entryService.save(newEntry);
        }
        return "redirect:/notes/" + note.getId();
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id) {
        noteService.delete(id);
        return "redirect:/notes";
    }
}
