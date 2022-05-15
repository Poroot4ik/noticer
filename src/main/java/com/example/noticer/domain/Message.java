package com.example.noticer.domain;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Component()
@Entity // These tells Hibernate to make a table out of this class
@Scope("prototype")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @NotEmpty(message = "Заполните сообщение")
    private String text;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "messageTag_id")
    @Valid
    private MessageTag tag;

    public Message() {
    }

    public Message(String text, MessageTag tag) {
        this.text = text;
        this.tag = tag;
    }

    public String getTagName() {
        return tag != null ? tag.getTagName() : "<none>";
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