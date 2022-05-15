package com.example.noticer.controller;

import com.example.noticer.check.MyCheckObject;
import com.example.noticer.domain.Message;
import com.example.noticer.domain.MessageTag;
import com.example.noticer.repos.MessageRepo;
import com.example.noticer.repos.MessageTagRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/messages")
public class MessageController {
    @Autowired
    private MessageRepo messageRepo;

    @Autowired
    private MessageTagRepo messageTagRepo;

    @Autowired
    private MessageTag messageTag;

    @GetMapping()
    public String index(Map<String, Object> model) {
        model.put("tags", messageTagRepo.findAll());
        return "messages/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Integer id, Map<String, Object> model) {
        model.put("message",messageRepo.findById(id).get());
        return "messages/show";
    }

    @GetMapping("/new")
    public String newMessage(@ModelAttribute(value="message") Message message, Map<String, Object> model) {
        return "messages/new";
    }

    @PostMapping()
    public String create(@ModelAttribute(value="message") Message message, Map<String, Object> model) {

        if (!message.getTagName().isEmpty()){
            MessageTag tag = messageTagRepo.findByTagName(message.getTagName());
            if (tag != null) {
                message.setTag(tag);
            }
        }

        if (!MyCheckObject.isObjectFieldsNull(message) && !message.getText().isEmpty() && !message.getTagName().isEmpty()) {
            messageTagRepo.save(message.getTag());
            messageRepo.save(message);
        }

        return "redirect:/messages";
    }

    @GetMapping("/{id}/edit")
    public String edit(Map<String, Object> model, @PathVariable("id") Integer id) {
        model.put("message", messageRepo.findById(id).get());
        return "messages/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute(value="message") Message message) {

        if (!message.getTagName().isEmpty()){
            MessageTag tag = messageTagRepo.findByTagName(message.getTagName());
            if (tag != null) {
                message.setTag(tag);
            }
        }

        if (!MyCheckObject.isObjectFieldsNull(message) && !message.getText().isEmpty() && !message.getTagName().isEmpty()) {
            messageTagRepo.save(message.getTag());
            messageRepo.save(message);
        }

        return "redirect:/messages";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id, Map<String, Object> model) {
        Optional<Message> message = messageRepo.findById(id);
        MessageTag messageTag = message.get().getTag();
        messageRepo.deleteById(id);
        List<Message> messages =messageRepo.findByTag(messageTag);
        if (messages.isEmpty()) {
            messageTagRepo.deleteById(messageTag.getId());
        }

        return "redirect:/messages";
    }

   /* @PostMapping("filter")
    public String filter(@RequestParam String filter, Map<String, Object> model) {

        Iterable<Message> messages;
        List<MessageTag> tags = new ArrayList<>();

        if (filter.isEmpty()) {
*//*            viewMessageList(model);*//*
            return "main";
        }

        messageTag = messageTagRepo.findByTagName(filter);
        tags.add(messageTag);

        if (messageTag != null) {
            messages = messageRepo.findByTag(messageTag);
        }
        else {
            messages = messageRepo.findAll();
        }
        model.put("tags", tags);
        model.put("messages", messages);
        return "main";
    }*/

}
