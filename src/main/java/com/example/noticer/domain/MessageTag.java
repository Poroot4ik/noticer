package com.example.noticer.domain;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Set;

@Entity // These tells Hibernate to make a table out of this class
@Table(name = "messageTag")
@Component()
@Scope("prototype")
public class MessageTag {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    @OneToMany(mappedBy = "tag",cascade=CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<Message> messages;

    public MessageTag() {
    }

    public MessageTag(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Set<Message> getMessages() {
        return messages;
    }

    public void setMessages(Set<Message> messages) {
        this.messages = messages;
    }
}
