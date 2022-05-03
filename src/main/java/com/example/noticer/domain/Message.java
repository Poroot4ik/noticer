package com.example.noticer.domain;

import javax.persistence.*;
import java.util.List;

@Entity // These tells Hibernate to make a table out of this class
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String text;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "messageTag_id")
    private MessageTag tag;

    public Message() {
    }

    public Message(String text, MessageTag tag) {
        this.text = text;
        this.tag = tag;
    }

    public String getTagName() {
        return tag != null ? tag.getName() : "<none>";
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public MessageTag getTag() {
        return tag;
    }

    public void setTag(MessageTag tag) {
        this.tag = tag;
    }
}