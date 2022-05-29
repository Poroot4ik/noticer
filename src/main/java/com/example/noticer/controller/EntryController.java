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
@RequestMapping("/entries")
public class EntryController {

    private final EntryService entryService;
    private final NoteService noteService;

    @Autowired
    public EntryController(EntryService entryService, NoteService noteService) {
        this.entryService = entryService;
        this.noteService = noteService;
    }

/*    @GetMapping()
    public String index(Map<String, Object> model) {
        model.put("entries", noteService.findAll());
        return "entries/index";
    }*/

/*    @GetMapping("/{id}")
    public String show(@PathVariable("id") Integer id, Map<String, Object> model) {

        model.put("entry", entryService.findOne(id));
        return "entries/show";
    }*/

/*    @GetMapping("/new")
    public String newEntry(@ModelAttribute(value="entry") Entry entry, Map<String, Object> model) {
        return "entries/new";
    }*/

    @PostMapping("/{note_id}")
    public String create(@PathVariable("note_id") Integer note_id, @ModelAttribute(value="entry") @Valid Entry entry,
                         BindingResult bindingResult) {
        entry.setNote(noteService.findOne(note_id));
        if (bindingResult.hasErrors())
            return "redirect:/notes/" + entry.getNote().getId();
        entryService.save(entry);

        return "redirect:/notes/" + entry.getNote().getId();
    }

/*    @GetMapping("/{id}/edit")
    public String edit(Map<String, Object> model, @PathVariable("id") Integer id) {
        model.put("entry", entryService.findOne(id));
        return "entries/edit";
    }*/

    @PatchMapping("/{note_id}")
    public String edit(Map<String, Object> model, @PathVariable("note_id") Integer note_id, @ModelAttribute(value="note") Note note) {
//        Entry entry = entryService.findOne(id);
//        model.put("note", noteService.findOne(note_id));
//        entryService.save(entry);
//        model.put("entry", entry);
        return "redirect:/notes/" + note_id;
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute(value="entry") @Valid Entry entry,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "entries/edit";

        entryService.update(entry);

        return "redirect:/entries";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id) {
        entryService.delete(id);
        return "redirect:/entries";
    }

}
