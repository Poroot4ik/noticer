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

    @Autowired
    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping()
    public String index(Map<String, Object> model) {
        model.put("notes", noteService.findAll());
        return "notes/index";
    }

    @GetMapping("/{id}")
    public String show(@ModelAttribute(value="newEntry") Note note, @ModelAttribute(value="newEntry") Entry newEntry, Map<String, Object> model) {
/*        newEntry.setNote(note);
        note.getEntries().add(newEntry);*/
        model.put("note", note);
        return "notes/show";
    }

    @GetMapping("/new")
    public String newEntry(@ModelAttribute(value="note") Note note, @ModelAttribute(value="entries") Note entries,Map<String, Object> model) {
        return "notes/new";
    }

    @PostMapping()
    public String create(@ModelAttribute(value="note") @Valid Note note,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "notes/new";
        noteService.save(note);
        return "redirect:/notes/" + note.getId() +"?title="+note.getTitle();
    }

    @GetMapping("/{id}/edit")
    public String edit(Map<String, Object> model, @PathVariable("id") Integer id, @ModelAttribute(value="entry") Entry entry) {
        model.put("note", noteService.findOne(id));
        return "redirect:/notes/" + id;
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute(value="note") @Valid Note note, @ModelAttribute(value="entries") Entry entries,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "redirect:/notes/"+ note.getId();

        noteService.update(note);

        return "redirect:/notes/" + note.getId() +"?title="+note.getTitle();
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id) {
        noteService.delete(id);
        return "redirect:/notes";
    }
}
