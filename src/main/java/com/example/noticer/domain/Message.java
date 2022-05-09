package com.example.noticer.domain;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity // These tells Hibernate to make a table out of this class
@Component()
@Scope("prototype")
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

    public MessageTag getTag() {
        return tag;
    }

    public void setTag(MessageTag tag) {
        this.tag = tag;
    }
}