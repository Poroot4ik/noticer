package com.example.noticer.domain;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Component()
@Entity // These tells Hibernate to make a table out of this class
@Table(name = "entry")
@Scope("prototype")
public class Entry {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @NotNull
    private String text;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "note_id")
    @Valid
    private Note note;

    public Entry() {
    }

    public Entry(Integer id) {
        this.id = id;
    }

    public Entry(String text, Note note) {
        this.text = text;
        this.note = note;
    }

    public String getTitle() {
        return note != null ? note.getTitle() : "<none>";
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Note getNote() {
        return note;
    }

    public void setNote(Note note) {
        this.note = note;
    }
}